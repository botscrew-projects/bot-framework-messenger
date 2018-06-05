/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.domain.action.AfterSendMessage;
import com.botscrew.messengercdk.domain.action.BeforeSendMessage;
import com.botscrew.messengercdk.domain.internal.LockingQueue;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.Response;
import com.botscrew.messengercdk.model.outgoing.builder.GenericTemplate;
import com.botscrew.messengercdk.model.outgoing.builder.QuickReplies;
import com.botscrew.messengercdk.model.outgoing.builder.TextMessage;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.service.InterceptorsTrigger;
import com.botscrew.messengercdk.service.TokenizedSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Implementation of {@link TokenizedSender} used by default
 */
public class TokenizedSenderImpl implements TokenizedSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReportHandler.class);

    private final RestTemplate restTemplate;
    private final MessengerProperties properties;
    private final ThreadPoolTaskScheduler scheduler;
    private final TaskExecutor taskExecutor;
    private final InterceptorsTrigger interceptorsTrigger;

    private final Map<Long, LockingQueue<Request>> lockingRequests;

    public TokenizedSenderImpl(RestTemplate restTemplate,
                               MessengerProperties properties,
                               ThreadPoolTaskScheduler scheduler,
                               TaskExecutor taskExecutor,
                               InterceptorsTrigger interceptorsTrigger) {
        this.restTemplate = restTemplate;
        this.properties = properties;
        this.scheduler = scheduler;
        this.taskExecutor = taskExecutor;
        this.interceptorsTrigger = interceptorsTrigger;

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
        Request request = QuickReplies.builder()
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
        triggerBeforeMessageInterceptors(token, request);

        Long id = request.getRecipient().getId();
        LockingQueue<Request> queue = lockingRequests.computeIfAbsent(id, k -> new LockingQueue<>());
        queue.push(request);

        if (!queue.isLocked()) startSendRequests(token, queue);
    }

    private void startSendRequests(String token, LockingQueue<Request> lockingQueue) {
        taskExecutor.execute(() -> {
            if (lockingQueue.tryLock()) {
                while (true) {
                    Optional<Request> requestOpt = lockingQueue.getNextOrUnlock();
                    if (!requestOpt.isPresent()) break;

                    Request top = requestOpt.get();
                    sendRequest(top, token);
                }
            }
        });
    }

    private void sendRequest(Request request, String token) {
        try {
            Response response = restTemplate.postForObject(properties.getMessagingUrl(token), request, Response.class);
            triggerAfterMessageInterceptors(token, request, response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error(e.getResponseBodyAsString() + " for request: " + request);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage() + " for request: " + request);
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
