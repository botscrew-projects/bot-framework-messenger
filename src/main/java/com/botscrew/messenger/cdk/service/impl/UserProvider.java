package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.MessengerUser;

public interface UserProvider {

    MessengerUser getByChatId(Long chatId);
}
