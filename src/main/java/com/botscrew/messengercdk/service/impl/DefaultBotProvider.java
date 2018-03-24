package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.model.MessengerBot;
import com.botscrew.messengercdk.service.BotProvider;

public class DefaultBotProvider implements BotProvider {
    private final String defaultAccessToken;

    public DefaultBotProvider(String defaultAccessToken) {
        this.defaultAccessToken = defaultAccessToken;
    }

    @Override
    public MessengerBot getById(Long id) {
        return new MessengerBot(id, defaultAccessToken);
    }
}
