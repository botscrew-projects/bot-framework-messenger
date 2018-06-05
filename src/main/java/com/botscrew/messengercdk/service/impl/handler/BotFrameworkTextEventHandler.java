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

package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.TextContainer;
import com.botscrew.botframework.domain.argument.ArgumentType;
import com.botscrew.botframework.domain.argument.kit.SimpleArgumentKit;
import com.botscrew.botframework.domain.argument.wrapper.SimpleArgumentWrapper;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventHandler;

/**
 * Default handler for `Text` event
 */
public class BotFrameworkTextEventHandler implements EventHandler {
    private final TextContainer textContainer;

    public BotFrameworkTextEventHandler(TextContainer textContainer) {
        this.textContainer = textContainer;
    }

    @Override
    public EventType getHandlingEventType() {
        return EventType.TEXT;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        String text = messaging.getMessage().getText();

        SimpleArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.TEXT, new SimpleArgumentWrapper(text));

        textContainer.process(messengerUser, kit);
    }
}
