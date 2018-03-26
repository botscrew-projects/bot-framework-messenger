package com.botscrew.messengercdk.model;

/**
 * @author Den Boyko
 * @version 1.0
 */
public class DefaultMessengerBot implements MessengerBot {
    private String accessToken;
    private Long appId;
    public DefaultMessengerBot(Long appId, String accessToken){
        this.appId = appId;
        this.accessToken = accessToken;
    }
    @Override
    public Long getAppId() {
        return appId;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }
}
