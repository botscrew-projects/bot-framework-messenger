package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.PostbackContainer;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventHandler;

public class BotFrameworkQuickReplyEventHandler implements EventHandler {
    private final PostbackContainer postbackContainer;

    public BotFrameworkQuickReplyEventHandler(PostbackContainer postbackContainer) {
        this.postbackContainer = postbackContainer;
    }

    @Override
    public EventType getHandlingEventType() {
        return EventType.QUICK_REPLY;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        String payload = messaging.getMessage().getQuickReply().getPayload();
        postbackContainer.process(messengerUser, payload);
    }
}
