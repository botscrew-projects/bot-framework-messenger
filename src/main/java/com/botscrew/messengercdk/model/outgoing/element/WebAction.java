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

    public WebAction() {
    }

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
