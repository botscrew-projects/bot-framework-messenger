package com.botscrew.messengercdk.model.outgoing.request;

import com.botscrew.messengercdk.model.outgoing.button.Button;
import com.botscrew.messengercdk.model.outgoing.template.TemplateAttachment;
import com.botscrew.messengercdk.model.outgoing.template.button.ButtonTemplateMessage;
import com.botscrew.messengercdk.model.outgoing.template.button.ButtonTemplatePayload;

import java.util.ArrayList;
import java.util.List;

public class ButtonTemplateBuilder extends RequestBuilder<ButtonTemplateBuilder> {
    private String text;
    private List<Button> buttons = new ArrayList<>();

    public ButtonTemplateBuilder text(String text) {
        this.text = text;
        return this;
    }

    public ButtonTemplateBuilder buttons(List<Button> buttons) {
        this.buttons = buttons;
        return this;
    }

    public ButtonTemplateBuilder button(Button button) {
        buttons.add(button);
        return this;
    }

    @Override
    public Request build() {
        Request request = super.build();
        request.setMessage(new ButtonTemplateMessage(
                new TemplateAttachment(
                        new ButtonTemplatePayload(text, buttons))));
        return request;
    }
}
