package com.botscrew.messenger.cdk.model.outgoing.request;

import com.botscrew.messenger.cdk.model.outgoing.button.Button;
import com.botscrew.messenger.cdk.model.outgoing.template.TemplateAttachment;
import com.botscrew.messenger.cdk.model.outgoing.template.TemplateElement;
import com.botscrew.messenger.cdk.model.outgoing.template.list.ListTemplateMessage;
import com.botscrew.messenger.cdk.model.outgoing.template.list.ListTemplatePayload;
import com.botscrew.messenger.cdk.model.outgoing.template.list.TopElementStyle;

import java.util.ArrayList;
import java.util.List;

public class ListTemplateBuilder extends RequestBuilder<ListTemplateBuilder> {
    private List<TemplateElement> elements = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();
    private TopElementStyle topElementStyle;

    public ListTemplateBuilder elements(List<TemplateElement> elements) {
        this.elements = elements;
        return this;
    }

    public ListTemplateBuilder element(TemplateElement element) {
        elements.add(element);
        return this;
    }

    public ListTemplateBuilder buttons(List<Button> buttons) {
        this.buttons = buttons;
        return this;
    }

    public ListTemplateBuilder button(Button button) {
        buttons.add(button);
        return this;
    }

    public ListTemplateBuilder topElementStyle(TopElementStyle style) {
        this.topElementStyle = style;
        return this;
    }

    @Override
    public Request build() {
        Request request = super.build();
        request.setMessage(new ListTemplateMessage(
                new TemplateAttachment(
                        new ListTemplatePayload(elements, buttons, topElementStyle))));
        return request;
    }

}
