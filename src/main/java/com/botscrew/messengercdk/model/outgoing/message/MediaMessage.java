package com.botscrew.messengercdk.model.outgoing.message;

import com.botscrew.messengercdk.model.outgoing.attachment.TemplateAttachment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MediaMessage extends Message {
    private TemplateAttachment attachment;

    public MediaMessage(TemplateAttachment attachment) {
        this.attachment = attachment;
    }
}
