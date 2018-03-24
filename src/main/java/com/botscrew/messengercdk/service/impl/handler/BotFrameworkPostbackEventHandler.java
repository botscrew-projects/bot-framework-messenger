package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.PostbackContainer;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventHandler;

public class BotFrameworkPostbackEventHandler implements EventHandler {
    private final PostbackContainer postbackContainer;

    public BotFrameworkPostbackEventHandler(PostbackContainer postbackContainer) {
        this.postbackContainer = postbackContainer;
    }

    @Override
    public EventType getHandlingEventType() {
        return EventType.POSTBACK;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        String payload = messaging.getPostback().getPayload();
        postbackContainer.process(messengerUser, payload);
    }
}
