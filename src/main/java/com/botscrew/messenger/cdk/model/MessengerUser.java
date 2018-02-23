package com.botscrew.messenger.cdk.model;

import com.botscrew.framework.flow.model.ChatUser;


public interface MessengerUser extends ChatUser {
    Long getChatId();
    default MessengerBot getBot(){return new MessengerBot() {
        @Override
        public String getAccessToken() {
            return null;
        }

        @Override
        public String getAppId() {
            return null;
        }
    };}
}
