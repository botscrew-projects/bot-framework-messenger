/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.attachment.TemplateAttachment;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.GenericTemplateMessage;
import com.botscrew.messengercdk.model.outgoing.payload.GenericTemplatePayload;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;

import java.util.ArrayList;
import java.util.List;

public class GenericTemplate {

    private GenericTemplate() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private List<TemplateElement> elements;
        private Boolean sharable;
        private String imageAspectRatio;
        private List<QuickReply> quickReplies;
        private MessagingType messagingType;
        private String tag;

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

        public Builder makeSharable() {
            this.sharable = true;
            return this;
        }

        public Builder squareImageRatio() {
            this.imageAspectRatio = "square";
            return this;
        }

        public Builder horizontalImageRatio() {
            this.imageAspectRatio = "horizontal";
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public MessageRequest build() {
            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setTag(tag);
            GenericTemplatePayload genericTemplatePayload = new GenericTemplatePayload(elements);
            genericTemplatePayload.setSharable(this.sharable);
            genericTemplatePayload.setImageAspectRatio(this.imageAspectRatio);
            TemplateAttachment templateAttachment = new TemplateAttachment(genericTemplatePayload);

            List<QuickReply> replies = quickReplies.isEmpty() ? null : quickReplies;
            request.setMessage(new GenericTemplateMessage(replies, templateAttachment));
            request.setMessagingType(messagingType);

            return request;
        }
    }
}
