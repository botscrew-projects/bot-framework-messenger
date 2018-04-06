package com.botscrew.messengercdk.model.outgoing.message;

import com.botscrew.messengercdk.model.outgoing.attachment.ContentAttachment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentMessage extends Message {
    private ContentAttachment attachment;

    public ContentMessage(ContentAttachment attachment) {
        this.attachment = attachment;
    }
}
