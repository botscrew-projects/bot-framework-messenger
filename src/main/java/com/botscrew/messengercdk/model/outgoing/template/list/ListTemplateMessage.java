package com.botscrew.messengercdk.model.outgoing.template.list;

import com.botscrew.messengercdk.model.outgoing.Attachment;
import com.botscrew.messengercdk.model.outgoing.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTemplateMessage extends Message {
    private Attachment attachment;

    public ListTemplateMessage(Attachment attachment) {
        this.attachment = attachment;
    }
}
