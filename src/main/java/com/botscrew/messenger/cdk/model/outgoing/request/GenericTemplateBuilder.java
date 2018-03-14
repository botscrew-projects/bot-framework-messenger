package com.botscrew.messenger.cdk.model.outgoing.request;

import com.botscrew.messenger.cdk.model.outgoing.template.TemplateAttachment;
import com.botscrew.messenger.cdk.model.outgoing.template.TemplateElement;
import com.botscrew.messenger.cdk.model.outgoing.template.generic.GenericTemplateMessage;
import com.botscrew.messenger.cdk.model.outgoing.template.generic.GenericTemplatePayload;

import java.util.ArrayList;
import java.util.List;

public class GenericTemplateBuilder extends RequestBuilder<GenericTemplateBuilder> {
    private List<TemplateElement> elements = new ArrayList<>();

    public GenericTemplateBuilder elements(List<TemplateElement> elements) {
        this.elements = elements;
        return this;
    }

    public GenericTemplateBuilder element(TemplateElement element) {
        elements.add(element);
        return this;
    }

    @Override
    public Request build() {
        Request request = super.build();
        request.setMessage(new GenericTemplateMessage(
                new TemplateAttachment(
                        new GenericTemplatePayload(elements))));
        return request;
    }
}
