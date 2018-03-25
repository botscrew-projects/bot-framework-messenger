package com.botscrew.messengercdk.model.outgoing.message;

import com.botscrew.messengercdk.model.outgoing.attachment.Attachment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ButtonTemplateMessage extends Message {
    private Attachment attachment;

    public ButtonTemplateMessage(Attachment attachment) {
        this.attachment = attachment;
    }
}
