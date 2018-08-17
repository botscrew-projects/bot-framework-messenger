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
import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.enums.TopElementStyle;
import com.botscrew.messengercdk.model.outgoing.message.ListTemplateMessage;
import com.botscrew.messengercdk.model.outgoing.payload.ListTemplatePayload;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;

import java.util.ArrayList;
import java.util.List;

public class ListTemplate {

    private ListTemplate() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MessengerUser user;
        private List<TemplateElement> elements;
        private List<Button> buttons;
        private TopElementStyle topElementStyle;
        private MessagingType messagingType;
        private String tag;

        public Builder() {
            elements = new ArrayList<>();
            buttons = new ArrayList<>();
            messagingType = MessagingType.RESPONSE;
        }

        public Builder user(MessengerUser messengerUser) {
            this.user = messengerUser;
            return this;
        }

        public Builder addElement(TemplateElement element) {
            this.elements.add(element);
            return this;
        }

        public Builder elements(List<TemplateElement> elements) {
            this.elements = elements;
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

        public Builder topElementStyle(TopElementStyle style) {
            this.topElementStyle = style;
            return this;
        }

        public Builder messagingType(MessagingType messagingType) {
            this.messagingType = messagingType;
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

            ListTemplatePayload listTemplatePayload = new ListTemplatePayload(elements, buttons, topElementStyle);
            TemplateAttachment templateAttachment = new TemplateAttachment(listTemplatePayload);
            ListTemplateMessage listTemplateMessage = new ListTemplateMessage(templateAttachment);

            request.setMessage(listTemplateMessage);
            return request;
        }
    }
}
