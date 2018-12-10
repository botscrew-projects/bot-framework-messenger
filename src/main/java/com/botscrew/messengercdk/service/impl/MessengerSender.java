package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.sender.AbstractSender;
import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.domain.action.AfterSendMessage;
import com.botscrew.messengercdk.domain.action.BeforeSendMessage;
import com.botscrew.messengercdk.domain.internal.LockingQueue;
import com.botscrew.messengercdk.exception.InterceptorInterruptedException;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.incomming.Response;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.service.InterceptorsTrigger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class MessengerSender extends AbstractSender<MessengerBot, Request> {
    private final Map<Long, LockingQueue<Request>> lockingRequests = new ConcurrentHashMap<>();

    private final RestTemplate restTemplate;
    private final TaskExecutor taskExecutor;
    private final InterceptorsTrigger interceptorsTrigger;
    private final MessengerProperties properties;

    @Override
    public void send(MessengerBot bot, Request request) {
        post(bot.getAccessToken(), request);
    }

    private void post(String token, Request request) {
        log.debug("Posting message: \n{}", request);
        try {
            triggerBeforeMessageInterceptors(token, request);
        } catch (InterceptorInterruptedException e) {
            log.info(e.getMessage());
            return;
        }

        Long id = request.getRecipient().getId();
        LockingQueue<Request> queue = lockingRequests.computeIfAbsent(id, k -> new LockingQueue<>());
        queue.push(request);

        if (!queue.isLocked()) {
            startSendRequests(token, queue);
        }
    }

    private void startSendRequests(String token, LockingQueue<Request> lockingQueue) {
        taskExecutor.execute(() -> {
            if (lockingQueue.tryLock()) {
                while (true) {
                    Optional<Request> requestOpt = lockingQueue.getNextOrUnlock();

                    if (!requestOpt.isPresent()) {
                        break;
                    }

                    sendRequest(token, requestOpt.get());
                }
            }
        });
    }

    private void sendRequest(String token, Request request) {
        try {
            Response response = restTemplate.postForObject(properties.getMessagingUrl(token), request, Response.class);
            triggerAfterMessageInterceptors(token, request, response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error(e.getResponseBodyAsString() + " for request: " + request);
        } catch (Exception e) {
            log.error(e.getMessage() + " for request: " + request);
        }
    }


    private void triggerBeforeMessageInterceptors(String token, Request request) {
        BeforeSendMessage beforeSendMessage = new BeforeSendMessage(token, request);
        interceptorsTrigger.trigger(beforeSendMessage);
    }

    private void triggerAfterMessageInterceptors(String token, Request request, Response response) {
        AfterSendMessage afterSendMessage = new AfterSendMessage(token, request, response);
        interceptorsTrigger.trigger(afterSendMessage);
    }
}
