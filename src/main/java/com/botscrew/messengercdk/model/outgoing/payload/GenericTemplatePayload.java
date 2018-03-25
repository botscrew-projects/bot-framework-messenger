package com.botscrew.messengercdk.model.outgoing.payload;

import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GenericTemplatePayload extends TemplatePayload {
    private List<TemplateElement> elements;

    public GenericTemplatePayload(List<TemplateElement> elements) {
        super("generic");
        this.elements = elements;
    }
}
