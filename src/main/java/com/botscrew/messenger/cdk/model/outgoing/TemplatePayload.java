package com.botscrew.messenger.cdk.model.outgoing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TemplatePayload extends Payload {

    private List<GenericElement> elements;

    public TemplatePayload(List<GenericElement> elements) {
        super("generic");
        this.elements = elements;
    }
}
