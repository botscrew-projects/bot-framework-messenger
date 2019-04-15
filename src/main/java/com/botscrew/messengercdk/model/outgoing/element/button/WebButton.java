/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.model.outgoing.element.button;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebButton extends Button {
    private String url;
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonProperty("webview_height_ratio")
    private String webviewHeightRation;
    @JsonProperty("fallback_url")
    private String fallbackUrl;

    public WebButton() {
    }

    public WebButton(String title, String url) {
        super("web_url", title);
        this.url = url;
    }

    public void enableMessengerExtensions() {
        this.messengerExtensions = true;
    }

    public void makeCompactWebView() {
        webviewHeightRation = "compact";
    }

    public void makeTallWebView() {
        webviewHeightRation = "tall";
    }

    public void makeFullWebView() {
        webviewHeightRation = "full";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getMessengerExtensions() {
        return messengerExtensions;
    }

    public void setMessengerExtensions(Boolean messengerExtensions) {
        this.messengerExtensions = messengerExtensions;
    }

    public String getWebviewHeightRation() {
        return webviewHeightRation;
    }

    public void setWebviewHeightRation(String webviewHeightRation) {
        this.webviewHeightRation = webviewHeightRation;
    }

    public String getFallbackUrl() {
        return fallbackUrl;
    }

    public void setFallbackUrl(String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
    }

    public static class Builder {
        private String title;
        private String url;
        private Boolean messengerExtensions = false;
        private String webviewHeightRation;
        private String fallbackUrl;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
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

        public WebButton build() {
            WebButton webButton = new WebButton(this.title, url);
            webButton.setMessengerExtensions(this.messengerExtensions);
            webButton.setWebviewHeightRation(this.webviewHeightRation);
            webButton.setFallbackUrl(this.fallbackUrl);
            return webButton;
        }
    }
}
