package com.botscrew.messengercdk.model;

import com.botscrew.botframework.model.ChatUser;

public interface MessengerUser extends ChatUser {
    Long getChatId();
}
