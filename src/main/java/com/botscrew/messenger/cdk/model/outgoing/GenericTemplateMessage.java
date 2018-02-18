package com.botscrew.messenger.cdk.model.outgoing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GenericTemplateMessage extends QuickRepliesMessage {

    private Attachment attachment;

    public GenericTemplateMessage(Attachment attachment) {
        super(null, null);
        this.attachment = attachment;
    }

    public GenericTemplateMessage(List<QuickReply> quickReplies, Attachment attachment) {
        super(null, quickReplies);
        this.attachment = attachment;
    }
}
