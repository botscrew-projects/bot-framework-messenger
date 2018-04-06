package com.botscrew.messengercdk.model.outgoing.element.button;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebViewButton extends WebButton {
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions = true;
    @JsonProperty("webview_height_ratio")
    private String webviewHeightRation;

    public WebViewButton(String title, String url, String webviewHeightRation) {
        super(title, url);
        this.webviewHeightRation = webviewHeightRation;
    }
}
