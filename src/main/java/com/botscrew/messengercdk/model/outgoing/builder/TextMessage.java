package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.message.Message;
import com.botscrew.messengercdk.model.outgoing.MessagingType;
import com.botscrew.messengercdk.model.outgoing.request.Request;

public class TextMessage {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private MessagingType messagingType;
        private String text;

        public Builder user(MessengerUser user) {
            this.user = user;
            return this;
        }

        public Builder messagingType(MessagingType messagingType) {
            this.messagingType = messagingType;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Request build() {
            Request request = new Request();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);
            request.setMessage(new Message(text));
            return request;
        }

    }
}
