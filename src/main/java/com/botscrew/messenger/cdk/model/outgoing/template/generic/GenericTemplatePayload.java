package com.botscrew.messenger.cdk.model.outgoing.template.generic;

import com.botscrew.messenger.cdk.model.outgoing.template.Payload;
import com.botscrew.messenger.cdk.model.outgoing.template.TemplateElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GenericTemplatePayload extends Payload {
    private List<TemplateElement> elements;

    public GenericTemplatePayload(List<TemplateElement> elements) {
        super("generic");
        this.elements = elements;
    }
}
