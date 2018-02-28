package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.incomming.*;
import com.botscrew.messenger.cdk.service.EventTypeResolver;

public class DefaultEventTypeResolver implements EventTypeResolver {

    @Override
    public EventType resolve(Messaging messaging) {
        if(echoAvailable(messaging)) return EventType.ECHO;
        if (quickReplyAvailable(messaging)) return EventType.QUICK_REPLY;
        if (textAvailable(messaging)) return EventType.TEXT;
        if (postbackAvailable(messaging)) return EventType.POSTBACK;
        if (locationAvailable(messaging)) return EventType.LOCATION;

        return EventType.UNDEFINED;
    }



    private boolean echoAvailable(Messaging messaging) {
        return messaging.getMessage() != null && messaging.getMessage().isEcho();
    }

    private boolean textAvailable(Messaging messaging) {
        return messaging.getMessage() != null
                && !isNullOrEmpty(messaging.getMessage().getText());
    }

    private boolean postbackAvailable(Messaging messaging) {
        return messaging.getPostback() != null
                && !isNullOrEmpty(messaging.getPostback().getPayload());
    }

    private boolean quickReplyAvailable(Messaging messaging) {
        Message m = messaging.getMessage();
        return m != null && m.getQuickReply() != null
                && !isNullOrEmpty(m.getQuickReply().getPayload());
    }

    private boolean locationAvailable(Messaging messaging) {
        Message message = messaging.getMessage();
        if (message != null && !message.getAttachments().isEmpty()) {
            Payload payload = message.getAttachments().get(0).getPayload();
            return payload != null && payload.getCoordinates() != null;
        }
        return false;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

}
