package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;

public interface EventTypeResolver {
    EventType resolve(Messaging messaging);
}
