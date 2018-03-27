package com.botscrew.messengercdk.service.impl;


import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Message;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.model.incomming.Payload;
import com.botscrew.messengercdk.service.EventHandler;
import com.botscrew.messengercdk.service.EventTypeResolver;

public class DefaultEventTypeResolver implements EventTypeResolver {

    @Override
    public EventType resolve(Messaging messaging) {
        if (echoAvailable(messaging)) {
            return EventType.ECHO;
        }
        if (quickReplyAvailable(messaging)) {
            return EventType.QUICK_REPLY;
        }
        if (textAvailable(messaging)) {
            return EventType.TEXT;
        }
        if (postbackAvailable(messaging)) {
            return EventType.POSTBACK;
        }
        if (locationAvailable(messaging)) {
            return EventType.LOCATION;
        }
        return EventType.UNDEFINED;
    }

    private boolean echoAvailable(Messaging messaging) {
        return messaging.getMessage() != null && messaging.getMessage().isEcho();
    }

    private boolean textAvailable(Messaging messaging) {
        return messaging.getMessage() != null && isNotNullAndEmpty(messaging.getMessage().getText());
    }

    private boolean postbackAvailable(Messaging messaging) {
        return messaging.getPostback() != null && isNotNullAndEmpty(messaging.getPostback().getPayload());
    }

    private boolean quickReplyAvailable(Messaging messaging) {
        Message m = messaging.getMessage();
        return m != null && m.getQuickReply() != null && isNotNullAndEmpty(m.getQuickReply().getPayload());
    }

    private boolean locationAvailable(Messaging messaging) {
        Message message = messaging.getMessage();
        if (message != null && !message.getAttachments().isEmpty()) {
            Payload payload = message.getAttachments().get(0).getPayload();
            return payload != null && payload.getCoordinates() != null;
        }
        return false;
    }

    private boolean isNotNullAndEmpty(String string) {
        return string != null && !string.isEmpty();
    }

}
