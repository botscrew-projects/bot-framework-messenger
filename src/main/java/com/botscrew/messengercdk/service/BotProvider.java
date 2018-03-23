package com.botscrew.messengercdk.service;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.botframework.domain.user.Chat;

public interface BotProvider {
    Bot getById(Long id);
}
