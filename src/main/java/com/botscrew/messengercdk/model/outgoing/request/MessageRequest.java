package com.botscrew.messengercdk.model.outgoing.request;

import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest extends Request {

    @JsonProperty("messaging_type")
    private MessagingType messagingType;
    private Message message;

    public MessagingType getMessagingType() {
        return messagingType;
    }

    public void setMessagingType(MessagingType messagingType) {
        this.messagingType = messagingType;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "messagingType=" + messagingType +
                ", message=" + message +
                '}';
    }
}
