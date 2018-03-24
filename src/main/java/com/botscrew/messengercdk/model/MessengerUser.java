package com.botscrew.messengercdk.model;


import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.botframework.domain.user.Platform;
import com.botscrew.botframework.domain.user.PlatformUser;

public interface MessengerUser extends PlatformUser {
    Long getChatId();

    @Override
    default Bot getBot() {
        return new MessengerBot(null, null);
    }
}
