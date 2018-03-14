package com.botscrew.messenger.cdk.model;

import com.botscrew.botframework.model.ChatUser;

public interface MessengerUser extends ChatUser {
    Long getChatId();
}
