package com.botscrew.messenger.cdk.model.outgoing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TemplateAttachment extends Attachment {

    private TemplatePayload payload;

    public TemplateAttachment(TemplatePayload payload) {
        super("template");
        this.payload = payload;
    }
}
