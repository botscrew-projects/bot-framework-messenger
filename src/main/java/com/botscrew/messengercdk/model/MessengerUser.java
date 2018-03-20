package com.botscrew.messengercdk.model;


import com.botscrew.botframework.domain.ChatUser;

public interface MessengerUser extends ChatUser {
    Long getChatId();
}
