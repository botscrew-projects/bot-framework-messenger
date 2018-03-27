package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.messengercdk.model.DefaultMessengerBot;
import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.service.UserProvider;

public class DefaultUserProvider implements UserProvider {

    @Override
    public MessengerUser getByChatIdAndBotId(Long chatId, Long appId) {
        return new MessengerUser() {
            @Override
            public Bot getBot() {
                return new DefaultMessengerBot(appId, null);
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
