package com.botscrew.messengercdk.model.outgoing.message;

import com.botscrew.messengercdk.model.outgoing.attachment.ContentAttachment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentMessage extends Message {
    private ContentAttachment attachment;

    public AttachmentMessage(ContentAttachment attachment) {
        this.attachment = attachment;
    }
}
