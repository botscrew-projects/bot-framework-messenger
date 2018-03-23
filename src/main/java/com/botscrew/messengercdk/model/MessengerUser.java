package com.botscrew.messengercdk.model;


import com.botscrew.botframework.domain.user.Chat;
import com.botscrew.botframework.domain.user.Platform;
import com.botscrew.botframework.domain.user.PlatformUser;

public interface MessengerUser extends PlatformUser {
    Long getChatId();

    @Override
    default Chat getChat() {
        return () -> Platform.FB_MESSENGER;
    }
}
