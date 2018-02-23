package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.MessengerBot;
import com.botscrew.messenger.cdk.model.MessengerUser;

public interface UserProvider {
    MessengerUser getByChatIdAndBotId(Long chatId, MessengerBot messengerBot);
}
