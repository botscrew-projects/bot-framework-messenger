package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.MessengerBot;
import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.service.UserProvider;

public class DefaultUserProvider implements UserProvider {

    @Override
    public MessengerUser getByChatIdAndBotId(Long chatId, MessengerBot messengerBot) {
        return new MessengerUser() {
            @Override
            public Long getChatId() {
                return chatId;
            }

            @Override
            public String getState() {
                return "default";
            }
        };    }
}
