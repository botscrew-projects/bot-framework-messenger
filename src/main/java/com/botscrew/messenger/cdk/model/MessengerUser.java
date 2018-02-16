package com.botscrew.messenger.cdk.model;

import com.botscrew.framework.flow.model.ChatUser;


public interface MessengerUser extends ChatUser {
    Long getChatId();
}
