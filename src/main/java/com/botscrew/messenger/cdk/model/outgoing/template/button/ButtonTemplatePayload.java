package com.botscrew.messenger.cdk.model.outgoing.template.button;

import com.botscrew.messenger.cdk.model.outgoing.button.Button;
import com.botscrew.messenger.cdk.model.outgoing.template.Payload;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ButtonTemplatePayload extends Payload {
    private String text;
    private List<Button> buttons;

    public ButtonTemplatePayload(String text, List<Button> buttons) {
        super("button");
        this.text = text;
        this.buttons = buttons;
    }
}
