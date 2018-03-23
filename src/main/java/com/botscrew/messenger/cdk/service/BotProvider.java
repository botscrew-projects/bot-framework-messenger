package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.MessengerBot;

/**
 * @author Den Boyko
 * @version 1.0
 */
public interface BotProvider {
    MessengerBot findById(Long id);
}
