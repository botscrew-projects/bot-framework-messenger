package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.MessengerBot;

public interface BotProvider {
    MessengerBot getById(Long id);
}
