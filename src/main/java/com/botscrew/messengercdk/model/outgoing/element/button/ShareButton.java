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

import com.botscrew.messengercdk.model.outgoing.message.Message;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShareButton extends Button {
    private static final String TYPE = "element_share";

    @JsonProperty("share_contents")
    private Message shareContent;

    public ShareButton() {
        super(TYPE, null);
    }

    public ShareButton(Message message) {
        super(TYPE, null);
        this.shareContent = message;
    }

    public ShareButton(MessageRequest request) {
        super(TYPE, null);
        this.shareContent = request.getMessage();
    }

    public void setShareContent(Message share) {
        this.shareContent = share;
    }

    public Message getShareContent() {
        return shareContent;
    }

    public void setShareContent(MessageRequest request) {
        this.shareContent = request.getMessage();
    }
}
