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
import com.botscrew.messengercdk.model.outgoing.request.Request;

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

        public Request build() {
            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);

            ListTemplatePayload listTemplatePayload = new ListTemplatePayload(elements, buttons, topElementStyle);
            TemplateAttachment templateAttachment = new TemplateAttachment(listTemplatePayload);
            ListTemplateMessage listTemplateMessage = new ListTemplateMessage(templateAttachment);

            request.setMessage(listTemplateMessage);
            return request;
        }
    }
}
