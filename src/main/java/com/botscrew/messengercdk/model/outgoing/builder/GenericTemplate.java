package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.MessagingType;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.model.outgoing.attachment.TemplateAttachment;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.message.GenericTemplateMessage;
import com.botscrew.messengercdk.model.outgoing.payload.GenericTemplatePayload;

import java.util.ArrayList;
import java.util.List;

public class GenericTemplate {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private List<TemplateElement> elements;
        private List<QuickReply> quickReplies;
        private MessagingType messagingType;

        Builder() {
            this.elements = new ArrayList<>();
            this.quickReplies = new ArrayList<>();
            this.messagingType = MessagingType.RESPONSE;
        }

        public Builder user(MessengerUser user) {
            this.user = user;
            return this;
        }

        public Builder addElement(TemplateElement templateElement) {
            this.elements.add(templateElement);
            return this;
        }

        public Builder elements(List<TemplateElement> templateElements) {
            this.elements = templateElements;
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

        public Builder messagingType(MessagingType messagingType) {
            this.messagingType = messagingType;
            return this;
        }

        public Request build() {
            Request request = new Request();
            request.setRecipient(new UserInfo(user.getChatId()));
            GenericTemplatePayload genericTemplatePayload = new GenericTemplatePayload(elements);
            TemplateAttachment templateAttachment = new TemplateAttachment(genericTemplatePayload);

            List<QuickReply> replies = quickReplies.isEmpty() ? null : quickReplies;
            request.setMessage(new GenericTemplateMessage(replies, templateAttachment));
            request.setMessagingType(messagingType);

            return request;
        }
    }
}
