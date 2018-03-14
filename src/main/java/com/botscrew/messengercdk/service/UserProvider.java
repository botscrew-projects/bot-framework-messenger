package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.MessengerUser;

public interface UserProvider {
    MessengerUser getByChatId(Long chatId);
}
