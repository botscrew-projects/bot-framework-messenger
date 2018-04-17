package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.*;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.QuickReplyMessage;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;

import java.util.ArrayList;
import java.util.List;

public class QuickReplies {

    private QuickReplies() {
    }

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

        public Builder postback(String title, String payload) {
            return addQuickReply(new PostbackQuickReply(title, payload));
        }

        public Builder location() {
            return addQuickReply(new LocationQuickReply());
        }

        public Builder phone() {
            return addQuickReply(new PhoneQuickReply());
        }

        public Builder email() {
            return addQuickReply(new EmailQuickReply());
        }

        public Builder quickReplies(List<QuickReply> quickReplies) {
            this.quickReplies = quickReplies;
            return this;
        }

        public MessageRequest build() {
            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);

            QuickReplyMessage message = new QuickReplyMessage(text, quickReplies);
            request.setMessage(message);

            return request;
        }
    }
}
