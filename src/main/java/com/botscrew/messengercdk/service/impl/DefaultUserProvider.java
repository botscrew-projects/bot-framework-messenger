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

package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.service.UserProvider;

/**
 * Implementation of {@link UserProvider} used by default
 */
public class DefaultUserProvider implements UserProvider {

    @Override
    public MessengerUser getByChatIdAndPageId(Long chatId, Long pageId) {
        return new MessengerUser() {
            @Override
            public Bot getBot() {
                return new MessengerBot(pageId, null);
            }

            @Override
            public Long getChatId() {
                return chatId;
            }

            @Override
            public String getState() {
                return "default";
            }
        };
    }
}
