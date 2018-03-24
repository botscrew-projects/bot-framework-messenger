package com.botscrew.messengercdk.model;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.botframework.domain.user.Platform;

public class MessengerBot implements Bot {
    private final Long id;
    private final String accessToken;

    public MessengerBot(Long id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    @Override
    public Platform getPlatform() {
        return Platform.FB_MESSENGER;
    }

    public Long getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
