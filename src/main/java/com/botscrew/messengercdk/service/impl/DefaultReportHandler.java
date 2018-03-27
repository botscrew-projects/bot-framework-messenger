package com.botscrew.messengercdk.service.impl;

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

@Slf4j
public class DefaultReportHandler implements ReportHandler {
    private final EventTypeResolver typeResolver;
    private final TaskExecutor taskExecutor;
    private final BotProvider botProvider;
    private final UserProvider userProvider;
    private final Map<EventType, EventHandler> eventHandlers;

    public DefaultReportHandler(EventTypeResolver typeResolver,
                                TaskExecutor taskExecutor,
                                List<EventHandler> handlers,
                                BotProvider botProvider,
                                UserProvider userProvider) {
        this.typeResolver = typeResolver;
        this.taskExecutor = taskExecutor;
        this.botProvider = botProvider;
        this.userProvider = userProvider;
        this.eventHandlers = new EnumMap<>(EventType.class);

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
            log.debug("Messenger report: {}", report);
            for (MessagingBundle bundle : report.getEntry()) {
                handleMessagingBundle(bundle);
            }
        });
    }

    private void handleMessagingBundle(MessagingBundle bundle) {
        for (Messaging messaging : bundle.getMessaging()) {
            EventType type = typeResolver.resolve(messaging);

            Long pageId = getPageId(messaging);
            Long userId = getUserId(messaging);

            MessengerBot messengerBot = botProvider.getById(pageId);
            if (messengerBot == null) throw new MessengerCDKException("Bot provider returns NULL for page id: " + pageId);
            MessengerUser user = userProvider.getByChatIdAndBotId(userId, messengerBot.getId());

            eventHandlers.get(type).handle(user, messaging);
        }
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
