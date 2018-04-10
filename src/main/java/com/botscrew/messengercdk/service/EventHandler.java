package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;

public interface EventHandler {
    EventType getHandlingEventType();

    void handle(MessengerUser messengerUser, Messaging messaging);
}
