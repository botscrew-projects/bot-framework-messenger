package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.model.incomming.Messaging;

public class PreMessageProcessingAction implements MessengerAction {
    private final Messaging messaging;

    public PreMessageProcessingAction(Messaging messaging) {
        this.messaging = messaging;
    }

    public Messaging getMessaging() {
        return messaging;
    }
}
