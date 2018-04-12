package com.botscrew.messengercdk.model.outgoing.element;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebAction {

    private String type = "web_url";
    private String url;
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonProperty("webview_height_ratio")
    private String webviewHeightRation;
    @JsonProperty("fallback_url")
    private String fallbackUrl;

    public WebAction(String url) {
        this.url = url;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String url;
        private Boolean messengerExtensions = false;
        private String webviewHeightRation;
        private String fallbackUrl;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder enableMessengerExtensions() {
            this.messengerExtensions = true;
            return this;
        }

        public Builder makeCompactWebView() {
            this.webviewHeightRation = "compact";
            return this;
        }

        public Builder makeTallWebView() {
            this.webviewHeightRation = "tall";
            return this;
        }

        public Builder makeFullWebView() {
            this.webviewHeightRation = "full";
            return this;
        }

        public Builder webviewHeightRatio(String ratio) {
            this.webviewHeightRation = ratio;
            return this;
        }

        public Builder fallbackUrl(String fallbackUrl) {
            this.fallbackUrl = fallbackUrl;
            return this;
        }

        public WebAction build() {
            WebAction action = new WebAction(url);
            action.setMessengerExtensions(this.messengerExtensions);
            action.setWebviewHeightRation(this.webviewHeightRation);
            action.setFallbackUrl(this.fallbackUrl);
            return action;
        }
    }
}
