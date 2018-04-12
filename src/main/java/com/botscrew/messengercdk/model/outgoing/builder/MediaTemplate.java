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

        public MessageRequest build() {
            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);

            MediaPayload payload = new MediaPayload(Collections.singletonList(element));
            TemplateAttachment attachment = new TemplateAttachment(payload);
            MediaMessage mediaMessage = new MediaMessage(attachment);

            request.setMessage(mediaMessage);

            return request;
        }
    }
}
