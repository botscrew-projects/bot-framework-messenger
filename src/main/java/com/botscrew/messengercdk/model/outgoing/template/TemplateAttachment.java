package com.botscrew.messengercdk.model.outgoing.template;

import com.botscrew.messengercdk.model.outgoing.Attachment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TemplateAttachment extends Attachment {
    private Payload payload;

    public TemplateAttachment(Payload payload) {
        super("template");
        this.payload = payload;
    }
}
