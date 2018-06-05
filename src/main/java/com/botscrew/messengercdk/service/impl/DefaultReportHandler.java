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

import com.botscrew.messengercdk.domain.action.GetEvent;
import com.botscrew.messengercdk.domain.action.ProcessedEvent;
import com.botscrew.messengercdk.exception.MessengerCDKException;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.model.incomming.MessagingBundle;
import com.botscrew.messengercdk.model.incomming.Report;
import com.botscrew.messengercdk.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link ReportHandler} used by default
 */
@Slf4j
public class DefaultReportHandler implements ReportHandler {
    private final EventTypeResolver typeResolver;
    private final TaskExecutor taskExecutor;
    private final BotProvider botProvider;
    private final UserProvider userProvider;
    private final Map<EventType, EventHandler> eventHandlers;
    private final InterceptorsTrigger interceptorsTrigger;
    private final ExceptionHandler exceptionHandler;

    public DefaultReportHandler(EventTypeResolver typeResolver,
                                TaskExecutor taskExecutor,
                                List<EventHandler> handlers,
                                BotProvider botProvider,
                                UserProvider userProvider,
                                InterceptorsTrigger interceptorsTrigger,
                                ExceptionHandler exceptionHandler) {
        this.typeResolver = typeResolver;
        this.taskExecutor = taskExecutor;
        this.botProvider = botProvider;
        this.userProvider = userProvider;
        this.eventHandlers = new EnumMap<>(EventType.class);
        this.interceptorsTrigger = interceptorsTrigger;
        this.exceptionHandler = exceptionHandler;


        for (EventHandler handler : handlers) {
            EventType handlingEventType = handler.getHandlingEventType();
            if (eventHandlers.containsKey(handlingEventType)) {
                throw new MessengerCDKException("Registered a few event handlers for type: " + handlingEventType);
            }
            eventHandlers.put(handlingEventType, handler);
        }
    }

    @Override
    public void handle(Report report) {
        taskExecutor.execute(() -> {
            try {
                log.debug("Messenger report: {}", report);
                for (MessagingBundle bundle : report.getEntry()) {
                    handleMessagingBundle(bundle);
                }
            } catch (Exception e) {
                boolean handled = exceptionHandler.handle(e);
                if (!handled) throw e;
            }
        });
    }

    private void handleMessagingBundle(MessagingBundle bundle) {
        for (Messaging messaging : bundle.getMessaging()) {
            EventType type = typeResolver.resolve(messaging);

            Long pageId = getPageId(messaging);
            Long userId = getUserId(messaging);

            MessengerBot messengerBot = botProvider.getById(pageId);
            if (messengerBot == null)
                throw new MessengerCDKException("Bot provider returns NULL for page id: " + pageId);

            MessengerUser user = userProvider.getByChatIdAndPageId(userId, messengerBot.getPageId());

            triggerGetEventInterceptors(messaging, type, user, messengerBot);
            EventHandler handler = eventHandlers.get(type);
            if (handler != null) {
                handler.handle(user, messaging);
                triggerProcessedEventInterceptors(messaging, type, user, messengerBot);
            } else {
                log.warn("No handler for type: " + type + " registered!");
            }
        }
    }

    private void triggerGetEventInterceptors(Messaging messaging,
                                             EventType type,
                                             MessengerUser messengerUser,
                                             MessengerBot messengerBot) {
        GetEvent getEvent = new GetEvent(messaging, type, messengerUser, messengerBot);
        interceptorsTrigger.trigger(getEvent);
    }

    private void triggerProcessedEventInterceptors(Messaging messaging, EventType type, MessengerUser user, MessengerBot messengerBot) {
        ProcessedEvent processedEvent = new ProcessedEvent(messaging, type, user, messengerBot);
        interceptorsTrigger.trigger(processedEvent);
    }

    private Long getUserId(Messaging messaging) {
        if (messaging.getMessage() != null && messaging.getMessage().isEcho()) {
            return messaging.getRecipient().getId();
        }
        return messaging.getSender().getId();
    }

    private Long getPageId(Messaging messaging) {
        if (messaging.getMessage() != null && messaging.getMessage().isEcho()) {
            return messaging.getSender().getId();
        }
        return messaging.getRecipient().getId();
    }
}
