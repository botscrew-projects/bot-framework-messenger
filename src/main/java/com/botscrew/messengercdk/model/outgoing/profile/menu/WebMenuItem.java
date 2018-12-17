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

package com.botscrew.messengercdk.model.outgoing.profile.menu;

import com.botscrew.messengercdk.model.outgoing.profile.menu.MenuItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebMenuItem extends MenuItem {
    private String url;
    @JsonProperty("webview_height_ratio")
    private String webViewHeightRatio;
    @JsonProperty("messenger_extensions")
    private boolean enableMessengerExtensions;

    public WebMenuItem(String title, String url) {
        super("web_url", title);
        this.url = url;
    }

    public Builder builder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebViewHeightRatio() {
        return webViewHeightRatio;
    }

    public void setWebViewHeightRatio(String webViewHeightRatio) {
        this.webViewHeightRatio = webViewHeightRatio;
    }

    public boolean isEnableMessengerExtensions() {
        return enableMessengerExtensions;
    }

    public void setEnableMessengerExtensions(boolean enableMessengerExtensions) {
        this.enableMessengerExtensions = enableMessengerExtensions;
    }

    @Override
    public String toString() {
        return "WebMenuItem{" +
                "title='" + getTitle() + '\'' +
                "url='" + url + '\'' +
                ", webViewHeightRatio='" + webViewHeightRatio + '\'' +
                ", enableMessengerExtensions=" + enableMessengerExtensions +
                '}';
    }

    public static class Builder {
        private String url;
        private String title;
        private String webViewHeightRatio;
        private boolean enableMessengerExtensions;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder webViewHeightRatio(String ratio) {
            this.webViewHeightRatio = ratio;
            return this;
        }

        public Builder fullView() {
            return webViewHeightRatio("full");
        }

        public Builder tallView() {
            return webViewHeightRatio("tall");
        }

        public Builder compactView() {
            return webViewHeightRatio("compact");
        }

        public Builder enableExtensions() {
            this.enableMessengerExtensions = true;
            return this;
        }

        public com.botscrew.messengercdk.model.outgoing.profile.menu.WebMenuItem build() {
            com.botscrew.messengercdk.model.outgoing.profile.menu.WebMenuItem item = new com.botscrew.messengercdk.model.outgoing.profile.menu.WebMenuItem(title, url);
            item.setWebViewHeightRatio(webViewHeightRatio);
            item.setEnableMessengerExtensions(enableMessengerExtensions);
            return item;
        }
    }
}
