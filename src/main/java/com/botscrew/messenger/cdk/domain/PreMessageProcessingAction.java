package com.botscrew.messenger.cdk.domain;

import com.botscrew.messenger.cdk.model.incomming.Messaging;
import lombok.Getter;

@Getter
public class PreMessageProcessingAction implements MessengerAction {

    private final Messaging messaging;

    public PreMessageProcessingAction(Messaging messaging) {
        this.messaging = messaging;
    }
}
