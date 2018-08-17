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
import com.botscrew.messengercdk.model.outgoing.element.media.MediaElement;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.MediaMessage;
import com.botscrew.messengercdk.model.outgoing.payload.MediaPayload;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;

import java.util.Collections;

public class MediaTemplate {

    private MediaTemplate() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MediaElement element;
        private MessengerUser user;
        private MessagingType messagingType = MessagingType.RESPONSE;
        private String tag;

        public Builder element(MediaElement element) {
            this.element = element;
            return this;
        }

        public Builder user(MessengerUser user) {
            this.user = user;
            return this;
        }

        public Builder messagingType(MessagingType type) {
            this.messagingType = type;
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

            MediaPayload payload = new MediaPayload(Collections.singletonList(element));
            TemplateAttachment attachment = new TemplateAttachment(payload);
            MediaMessage mediaMessage = new MediaMessage(attachment);

            request.setMessage(mediaMessage);

            return request;
        }
    }
}
