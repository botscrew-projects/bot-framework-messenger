package com.botscrew.messengercdk.model;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.botframework.domain.user.Platform;

public interface MessengerBot extends Bot {
    @Override
    default Platform getPlatform() {return Platform.FB_MESSENGER;}

    Long getAppId();

    String getAccessToken();
}
