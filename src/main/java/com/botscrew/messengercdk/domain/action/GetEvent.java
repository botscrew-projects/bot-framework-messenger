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

package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.domain.action.MessengerAction;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;

/**
 * Describes action after getting an event and before processing it
 */
public class GetEvent implements MessengerAction {

    private final Messaging messaging;
    private final EventType eventType;
    private final MessengerUser messengerUser;
    private final MessengerBot messengerBot;

    public GetEvent(Messaging messaging, EventType eventType, MessengerUser messengerUser, MessengerBot messengerBot) {
        this.messaging = messaging;
        this.eventType = eventType;
        this.messengerUser = messengerUser;
        this.messengerBot = messengerBot;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public EventType getEventType() {
        return eventType;
    }

    public MessengerUser getMessengerUser() {
        return messengerUser;
    }

    public MessengerBot getMessengerBot() {
        return messengerBot;
    }

    @Override
    public String toString() {
        return "GetEvent{" +
                "messaging=" + messaging +
                ", eventType=" + eventType +
                ", messengerUser=" + messengerUser +
                ", messengerBot=" + messengerBot +
                '}';
    }
}
