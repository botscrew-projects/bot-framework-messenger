package com.botscrew.messengercdk.model.outgoing.template.button;

import com.botscrew.messengercdk.model.outgoing.Attachment;
import com.botscrew.messengercdk.model.outgoing.Message;
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
