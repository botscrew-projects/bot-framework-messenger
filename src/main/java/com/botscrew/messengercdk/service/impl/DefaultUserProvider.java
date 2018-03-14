package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.service.UserProvider;

public class DefaultUserProvider implements UserProvider {
    @Override
    public MessengerUser getByChatId(Long chatId) {
        return new MessengerUser() {
            @Override
            public Long getChatId() {
                return chatId;
            }

            public String getState() {
                return "default";
            }
        };
    }
}
