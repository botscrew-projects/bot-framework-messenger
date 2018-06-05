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

package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.request.SenderActionRequest;

public class SenderAction {

    private SenderAction() {
    }

    public static SenderActionRequest typingOn(MessengerUser user) {
        return new SenderActionRequest(new UserInfo(user.getChatId()),
                com.botscrew.messengercdk.model.outgoing.enums.SenderAction.TYPING_ON);
    }

    public static SenderActionRequest typingOff(MessengerUser user) {
        return new SenderActionRequest(new UserInfo(user.getChatId()),
                com.botscrew.messengercdk.model.outgoing.enums.SenderAction.TYPING_OFF);
    }

    public static SenderActionRequest markSeen(MessengerUser user) {
        return new SenderActionRequest(new UserInfo(user.getChatId()),
                com.botscrew.messengercdk.model.outgoing.enums.SenderAction.MARK_SEEN);
    }
}
