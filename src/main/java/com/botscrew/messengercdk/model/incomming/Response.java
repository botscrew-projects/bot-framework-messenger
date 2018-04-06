package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("recipient_id")
    private Long recipientId;
    @JsonProperty("message_id")
    private String messageId;

    public Long getRecipientId() {
        return recipientId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "Response{" +
                "recipientId=" + recipientId +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
