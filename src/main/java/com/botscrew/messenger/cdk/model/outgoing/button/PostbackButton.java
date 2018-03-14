package com.botscrew.messenger.cdk.model.outgoing.button;

import com.botscrew.messenger.cdk.model.outgoing.button.Button;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostbackButton extends Button {

    private String payload;

    public PostbackButton(String title, String payload) {
        super("postback", title);
        this.payload = payload;
    }
}
