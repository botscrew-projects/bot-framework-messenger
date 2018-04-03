package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.domain.internal.LockingQueue;
import com.botscrew.messengercdk.exception.SendAPIException;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.outgoing.builder.GenericTemplate;
import com.botscrew.messengercdk.model.outgoing.builder.QuickRepliesMessage;
import com.botscrew.messengercdk.model.outgoing.builder.TextMessage;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.service.TokenizedSender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TokenizedSenderImpl implements TokenizedSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReportHandler.class);

    private final RestTemplate restTemplate;
    private final MessengerProperties properties;
    private final ThreadPoolTaskScheduler scheduler;

    private final TaskExecutor taskExecutor;
    private Map<Long, LockingQueue<Request>> lockingRequests;

    public TokenizedSenderImpl(RestTemplate restTemplate,
                               MessengerProperties properties,
                               ThreadPoolTaskScheduler scheduler,
                               TaskExecutor taskExecutor) {
        this.restTemplate = restTemplate;
        this.properties = properties;
        this.scheduler = scheduler;
        this.taskExecutor = taskExecutor;

        lockingRequests = new ConcurrentHashMap<>();
    }

    @Override
    public void send(String token, Request request) {
        post(token, request);
    }

    @Override
    public void send(String token, MessengerUser recipient, String text) {
        Request request = TextMessage.builder()
                .user(recipient)
                .text(text)
                .build();

        post(token, request);
    }

    @Override
    public ScheduledFuture send(String token, MessengerUser recipient, String text, Integer delayMillis) {
        return scheduler.schedule(() -> send(token, recipient, text), currentDatePlusMillis(delayMillis));
    }

    @Override
    public void send(String token, MessengerUser recipient, String text, List<QuickReply> quickReplies) {
        Request request = QuickRepliesMessage.builder()
                .user(recipient)
                .quickReplies(quickReplies)
                .text(text)
                .build();

        post(token, request);
    }

    @Override
    public ScheduledFuture send(String token, MessengerUser recipient, String text, List<QuickReply> quickReplies, Integer delayMillis) {
        return scheduler.schedule(() -> send(token, recipient, text, quickReplies), currentDatePlusMillis(delayMillis));
    }

    @Override
    public void send(String token, MessengerUser recipient, List<TemplateElement> elements) {
        Request request = GenericTemplate.builder()
                .user(recipient)
                .elements(elements)
                .build();

        post(token, request);
    }

    @Override
    public ScheduledFuture send(String token, MessengerUser recipient, List<TemplateElement> elements, Integer delayMillis) {
        return scheduler.schedule(() -> send(token, recipient, elements), currentDatePlusMillis(delayMillis));
    }

    @Override
    public void send(String token, MessengerUser recipient, List<TemplateElement> elements, List<QuickReply> quickReplies) {
        Request request = GenericTemplate.builder()
                .user(recipient)
                .elements(elements)
                .quickReplies(quickReplies)
                .build();

        post(token, request);
    }

    @Override
    public ScheduledFuture send(String token, MessengerUser recipient, List<TemplateElement> elements, List<QuickReply> quickReplies, Integer delayMillis) {
        return scheduler.schedule(() -> send(token, recipient, elements, quickReplies), currentDatePlusMillis(delayMillis));
    }

    @Override
    public ScheduledFuture send(String token, Request request, Integer delayMillis) {
        return scheduler.schedule(() -> send(token, request), currentDatePlusMillis(delayMillis));
    }

    private void post(String token, Request request) {
        LOGGER.debug("Posting message: \n{}", request);

        Long id = request.getRecipient().getId();
        LockingQueue<Request> queue = lockingRequests.computeIfAbsent(id, k -> new LockingQueue<>());
        queue.push(request);

        if (!queue.isLocked()) runRequest(token, queue);
    }

    private void runRequest(String token, LockingQueue<Request> lockingQueue) {
        taskExecutor.execute(() -> {
            if (lockingQueue.tryLock()) {
                while (true) {
                    if (!lockingQueue.hasNext()) {
                        lockingQueue.unlock();
                        break;
                    }
                    Request top = lockingQueue.poll();
                    try {
                        restTemplate.postForObject(properties.getMessagingUrl(token), top, String.class);
                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                        throw new SendAPIException(e.getResponseBodyAsString());
                    }
                }
            }
        });
    }

    private Date currentDatePlusMillis(Integer millis) {
        return addToDate(new Date(), Calendar.MILLISECOND, millis);
    }

    private Date addToDate(Date date, int calendarField, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    @Override
    public void send(MessengerBot bot, Request request) {
        post(bot.getAccessToken(), request);
    }
}
