package com.botscrew.messengercdk.config.property;

import com.botscrew.messengercdk.util.URL;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ToString
@Getter
@Setter
@ConfigurationProperties(prefix = "facebook.messenger")
public class MessengerProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessengerProperties.class);

    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    private static final String ACCESS_TOKEN_PARAM = "access_token";

    private String verifyToken = "test";
    private String accessToken;
    private String graphHost = "graph.facebook.com";
    private Integer graphPort = 443;
    private String graphApiVersion = "2.6";
    private String messagingPath = "/me/messages";
    private String graphProtocol = HTTPS;
    private String eventsPath = "/messenger/events";
    private String[] profileFields = {"first_name", "last_name", "profile_pic", "gender", "locale", "timezone"};
    private String appId;
    private String appAccessToken;

    private URL.Builder messagingUrlBuilder;
    private String defaultMessagingUrl;

    private URL.Builder profileUrlBuilder;
    private URL.Builder pageProfileUrlBuilder;
    private URL.Builder graphApiSubscriptionsUrlBuilder;

    @PostConstruct
    public void init() {
        createMessagingUrlBuilder();
        createDefaultMessagingUrl();
        createProfileUrlBuilder();
        createPageProfileUrlBuilder();
        createGraphApiSubscriptionsUrlBuilder();
    }

    public String getMessagingUrl() {
        return defaultMessagingUrl;
    }

    public String getPageProfileUrl() {
        return getPageProfileUrl(this.getAccessToken());
    }

    public String getPageProfileUrl(String token) {
        return pageProfileUrlBuilder
                .param(ACCESS_TOKEN_PARAM, token)
                .build().getValue();
    }

    public String getProfileUrl(String id) {
        return profileUrlBuilder
                .path("v" + graphApiVersion + "/" + id)
                .param(ACCESS_TOKEN_PARAM, this.accessToken)
                .build().getValue();
    }

    public String getProfileUrl(String id, String token) {
        return profileUrlBuilder
                .path("v" + graphApiVersion + "/" + id)
                .param(ACCESS_TOKEN_PARAM, token)
                .build().getValue();
    }

    private void createProfileUrlBuilder() {
        profileUrlBuilder = new URL.Builder()
                .protocol(graphProtocol)
                .host(graphHost)
                .port(graphPort)
                .param("fields", String.join(",", profileFields));
    }

    private void createPageProfileUrlBuilder() {
        pageProfileUrlBuilder = new URL.Builder()
                .protocol(graphProtocol)
                .host(graphHost)
                .port(graphPort)
                .path("v" + graphApiVersion + "/me/messenger_profile");
    }

    private void createGraphApiSubscriptionsUrlBuilder() {
        graphApiSubscriptionsUrlBuilder = new URL.Builder()
                .protocol(HTTPS)
                .host(graphHost)
                .port(graphPort)
                .path(appId + "/subscriptions");
    }

    public String getMessagingUrl(String token) {
        return messagingUrlBuilder
                .param(ACCESS_TOKEN_PARAM, token)
                .build()
                .getValue();
    }

    public String getGraphApiSubscriptionsUrl() {
        return graphApiSubscriptionsUrlBuilder
                .path(appId + "/subscriptions")
                .build().getValue();
    }

    public String getGraphApiSubscriptionsUrl(String appId, String appAccessToken) {
        return graphApiSubscriptionsUrlBuilder
                .path(appId + "/subscriptions")
                .param("app_id", appId)
                .param("access_token", appAccessToken)
                .build().getValue();
    }

    public String getGraphApiSubscriptionsUrl(String customAppId) {
        return graphApiSubscriptionsUrlBuilder
                .path(customAppId + "/subscriptions")
                .build().getValue();
    }

    private void createMessagingUrlBuilder() {
        messagingUrlBuilder = new URL.Builder()
                .protocol(graphProtocol)
                .host(graphHost)
                .port(graphPort)
                .path(messagingPath);
    }

    private void createDefaultMessagingUrl() {
        defaultMessagingUrl = messagingUrlBuilder
                .param(ACCESS_TOKEN_PARAM, accessToken)
                .port(graphPort)
                .build()
                .getValue();
    }
}
