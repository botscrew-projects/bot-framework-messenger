package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;

public class ProcessedEvent implements MessengerAction {
    private final Messaging messaging;
    private final EventType eventType;
    private final MessengerUser messengerUser;
    private final MessengerBot messengerBot;

    public ProcessedEvent(Messaging messaging,
                          EventType type,
                          MessengerUser user,
                          MessengerBot messengerBot) {
        this.messaging = messaging;
        this.eventType = type;
        this.messengerUser = user;
        this.messengerBot = messengerBot;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public EventType getEventType() {
        return eventType;
    }

    public MessengerUser getMessengerUser() {
        return messengerUser;
    }

    public MessengerBot getMessengerBot() {
        return messengerBot;
    }

    @Override
    public String toString() {
        return "ProcessedEvent{" +
                "messaging=" + messaging +
                ", eventType=" + eventType +
                ", messengerUser=" + messengerUser +
                ", messengerBot=" + messengerBot +
                '}';
    }
}
