package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.MessengerUser;

public interface UserProvider {
    MessengerUser getByChatId(Long chatId);
}
