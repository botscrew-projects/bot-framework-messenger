package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.attachment.Attachment;
import com.botscrew.messengercdk.model.outgoing.attachment.ContentAttachment;
import com.botscrew.messengercdk.model.outgoing.enums.MessagingType;
import com.botscrew.messengercdk.model.outgoing.message.ContentMessage;
import com.botscrew.messengercdk.model.outgoing.payload.ContentPayload;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;
import com.botscrew.messengercdk.model.outgoing.request.Request;

public class Content {

    private Content() {
    }

    public static Builder audio() {
        return new Builder(Attachment.Type.AUDIO);
    }

    public static Builder video() {
        return new Builder(Attachment.Type.VIDEO);
    }

    public static Builder image() {
        return new Builder(Attachment.Type.IMAGE);
    }

    public static Builder file() {
        return new Builder(Attachment.Type.FILE);
    }

    public static class Builder {
        private MessengerUser user;
        private MessagingType messagingType;

        private Attachment.Type type;

        private String url;
        private boolean isReusable;
        private Long attachmentId;

        public Builder(Attachment.Type type) {
            messagingType = MessagingType.RESPONSE;
            this.type = type;
        }

        public Builder user(MessengerUser user) {
            this.user = user;
            return this;
        }

        public Builder messagingType(MessagingType messagingType) {
            this.messagingType = messagingType;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder isReusable(boolean isReusable) {
            this.isReusable = isReusable;
            return this;
        }

        public Builder attachmentId(Long attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }

        public Request build() {
            ContentPayload payload;
            if (url != null) {
                payload = new ContentPayload(url, isReusable);
            } else {
                payload = new ContentPayload(attachmentId);
            }

            ContentAttachment attachment = new ContentAttachment(type, payload);

            ContentMessage message = new ContentMessage(attachment);

            MessageRequest request = new MessageRequest();
            request.setRecipient(new UserInfo(user.getChatId()));
            request.setMessagingType(messagingType);
            request.setMessage(message);
            return request;
        }
    }
}
