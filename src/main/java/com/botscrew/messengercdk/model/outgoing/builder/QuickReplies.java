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
        private String tag;

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

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public MessageRequest build() {
            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);
            request.setTag(tag);

            QuickReplyMessage message = new QuickReplyMessage(text, quickReplies);
            request.setMessage(message);

            return request;
        }
    }
}
