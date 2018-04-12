package com.botscrew.messengercdk.model.outgoing.attachment;

import com.botscrew.messengercdk.model.outgoing.payload.AttachmentPayload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentAttachment extends Attachment {
    private AttachmentPayload payload;

    public ContentAttachment(Type type, AttachmentPayload payload) {
        super(type);
        this.payload = payload;
    }


}
