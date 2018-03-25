package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.MessagingType;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.message.QuickReplyMessage;
import com.botscrew.messengercdk.model.outgoing.request.Request;

import java.util.ArrayList;
import java.util.List;

public class QuickRepliesMessage {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private MessagingType messagingType;
        private String text;
        private List<QuickReply> quickReplies;

        public Builder() {
            messagingType = MessagingType.RESPONSE;
            quickReplies = new ArrayList<>();
        }

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

        public Builder addQuickReply(QuickReply quickReply) {
            this.quickReplies.add(quickReply);
            return this;
        }

        public Builder quickReplies(List<QuickReply> quickReplies) {
            this.quickReplies = quickReplies;
            return this;
        }

        public Request build() {
            Request request = new Request();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);

            QuickReplyMessage message = new QuickReplyMessage(text, quickReplies);
            request.setMessage(message);

            return request;
        }
    }
}
