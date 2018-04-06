package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;

public class GetEvent implements MessengerAction {

    private final Messaging messaging;
    private final EventType eventType;
    private final MessengerUser messengerUser;
    private final MessengerBot messengerBot;

    public GetEvent(Messaging messaging, EventType eventType, MessengerUser messengerUser, MessengerBot messengerBot) {
        this.messaging = messaging;
        this.eventType = eventType;
        this.messengerUser = messengerUser;
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
        return "GetEvent{" +
                "messaging=" + messaging +
                ", eventType=" + eventType +
                ", messengerUser=" + messengerUser +
                ", messengerBot=" + messengerBot +
                '}';
    }
}
