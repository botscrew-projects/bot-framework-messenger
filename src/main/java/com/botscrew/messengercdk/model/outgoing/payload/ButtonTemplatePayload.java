package com.botscrew.messengercdk.model.outgoing.payload;

import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ButtonTemplatePayload extends TemplatePayload {
    private String text;
    private List<Button> buttons;

    public ButtonTemplatePayload(String text, List<Button> buttons) {
        super("button");
        this.text = text;
        this.buttons = buttons;
    }
}
