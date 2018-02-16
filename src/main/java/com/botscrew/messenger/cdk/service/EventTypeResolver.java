package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.incomming.EventType;
import com.botscrew.messenger.cdk.model.incomming.Messaging;

public interface EventTypeResolver {
    EventType resolve(Messaging messaging);
}
