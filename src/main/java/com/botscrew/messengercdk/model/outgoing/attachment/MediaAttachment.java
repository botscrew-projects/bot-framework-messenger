package com.botscrew.messengercdk.model.outgoing.attachment;

import com.botscrew.messengercdk.model.outgoing.element.media.MediaElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaAttachment extends Attachment {
    private MediaElement payload;

    public MediaAttachment(Attachment.Type type, MediaElement payload) {
        super(type);
        this.payload = payload;
    }
}
