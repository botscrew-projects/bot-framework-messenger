package com.botscrew.messengercdk.model.outgoing.attachment;

import com.botscrew.messengercdk.model.outgoing.payload.ContentPayload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentAttachment extends Attachment {
    private ContentPayload payload;

    public ContentAttachment(Attachment.Type type, ContentPayload payload) {
        super(type);
        this.payload = payload;
    }
}
