package com.botscrew.messenger.cdk.model.outgoing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebButton extends Button {

    private String url;

    public WebButton(String title, String url) {
        super("web_url", title);
        this.url = url;
    }
}
