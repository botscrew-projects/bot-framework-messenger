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
import com.botscrew.messengercdk.model.outgoing.attachment.Attachment;
import com.botscrew.messengercdk.model.outgoing.attachment.TemplateAttachment;
import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.ButtonTemplateMessage;
import com.botscrew.messengercdk.model.outgoing.payload.ButtonTemplatePayload;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;

import java.util.ArrayList;
import java.util.List;

public class ButtonTemplate {

    private ButtonTemplate() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private String text;
        private List<Button> buttons;
        private MessagingType messagingType;

        public Builder() {
            buttons = new ArrayList<>();
            messagingType = MessagingType.RESPONSE;
        }

        public Builder user(MessengerUser messengerUser) {
            this.user = messengerUser;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder addButton(Button button) {
            this.buttons.add(button);
            return this;
        }

        public Builder buttons(List<Button> buttons) {
            this.buttons = buttons;
            return this;
        }

        public Builder messagingType(MessagingType messagingType) {
            this.messagingType = messagingType;
            return this;
        }

        public MessageRequest build() {
            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));

            ButtonTemplatePayload payload = new ButtonTemplatePayload(text, buttons);
            Attachment attachment = new TemplateAttachment(payload);
            ButtonTemplateMessage buttonTemplateMessage = new ButtonTemplateMessage(attachment);

            request.setMessage(buttonTemplateMessage);
            request.setMessagingType(messagingType);

            return request;
        }

    }
}
