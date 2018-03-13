package com.botscrew.messenger.cdk.model.outgoing.template.list;

import com.botscrew.messenger.cdk.model.outgoing.Attachment;
import com.botscrew.messenger.cdk.model.outgoing.Message;
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
