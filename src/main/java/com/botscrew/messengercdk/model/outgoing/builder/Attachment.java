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
import com.botscrew.messengercdk.model.outgoing.attachment.ContentAttachment;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.AttachmentMessage;
import com.botscrew.messengercdk.model.outgoing.payload.AttachmentPayload;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;

public class Attachment {
    private Attachment() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private MessagingType messagingType = MessagingType.RESPONSE;
        private com.botscrew.messengercdk.model.outgoing.attachment.Attachment.Type type;
        private Long attachmentId;
        private String url;
        private Boolean isReusable;

        public Builder user(MessengerUser user) {
            this.user = user;
            return this;
        }

        public Builder messagingType(MessagingType type) {
            this.messagingType = type;
            return this;
        }

        public Builder type(com.botscrew.messengercdk.model.outgoing.attachment.Attachment.Type type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder attachmentId(Long attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }

        public Builder isReusable(Boolean isReusable) {
            this.isReusable = isReusable;
            return this;
        }

        public MessageRequest build() {
            MessageRequest request = new MessageRequest();
            request.setMessagingType(messagingType);
            request.setRecipient(new UserInfo(user.getChatId()));

            AttachmentPayload payload = new AttachmentPayload(url, isReusable);
            payload.setAttachmentId(attachmentId);

            ContentAttachment attachment = new ContentAttachment(type, payload);
            AttachmentMessage message = new AttachmentMessage(attachment);

            request.setMessage(message);

            return request;
        }
    }

}
