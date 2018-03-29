package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.messengercdk.model.DefaultMessengerBot;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.service.UserProvider;

public class DefaultUserProvider implements UserProvider {

    @Override
    public MessengerUser getByChatIdAndPageId(Long chatId, Long pageId) {
        return new MessengerUser() {
            @Override
            public Bot getBot() {
                return new DefaultMessengerBot(pageId, null);
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
