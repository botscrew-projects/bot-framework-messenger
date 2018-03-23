package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.MessengerBot;
import com.botscrew.messenger.cdk.service.BotProvider;

/**
 * @author Den Boyko
 * @version 1.0
 */
public class DefaultBotProvider implements BotProvider {

    private String accessToken;

    public DefaultBotProvider(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public MessengerBot findById(Long id) {
        return new MessengerBot() {
            @Override
            public String getAccessToken() {

                return accessToken;
            }

            @Override
            public String getAppId() {
                return null;        //TODO: setDefault appId from properties
            }
        };
    }
}
