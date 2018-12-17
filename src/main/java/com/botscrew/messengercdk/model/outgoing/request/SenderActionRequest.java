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

package com.botscrew.messengercdk.model.outgoing.request;

import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.enums.SenderAction;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SenderActionRequest extends Request {
    @JsonProperty("sender_action")
    private SenderAction senderAction;

    public SenderActionRequest() {
    }

    public SenderActionRequest(UserInfo recipient, SenderAction senderAction) {
        super(recipient);
        this.senderAction = senderAction;
    }

    public SenderAction getSenderAction() {
        return senderAction;
    }

    public void setSenderAction(SenderAction senderAction) {
        this.senderAction = senderAction;
    }

    @Override
    public String toString() {
        return "SenderActionRequest{" +
                "senderAction=" + senderAction +
                '}';
    }
}
