package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.service.UserProvider;

public class DefaultUserProvider implements UserProvider {

    @Override
    public MessengerUser getByChatId(Long chatId) {
        return new MessengerUser() {
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
