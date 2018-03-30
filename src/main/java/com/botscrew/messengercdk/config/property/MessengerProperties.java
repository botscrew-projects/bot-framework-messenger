package com.botscrew.messengercdk.config.property;

import com.botscrew.messengercdk.util.URL;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ToString
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

    private URL.Builder messagingUrlBuilder;
    private String defaultMessagingUrl;

    private URL.Builder profileUrlBuilder;
    private URL.Builder pageProfileUrlBuilder;

    @PostConstruct
    public void init() {
        createMessagingUrlBuilder();
        createDefaultMessagingUrl();
        createProfileUrlBuilder();
        createPageProfileUrlBuilder();
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

    public String getMessagingUrl(String token) {
        return messagingUrlBuilder
                .param(ACCESS_TOKEN_PARAM, token)
                .build()
                .getValue();
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

    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getGraphHost() {
        return graphHost;
    }

    public void setGraphHost(String graphHost) {
        this.graphHost = graphHost;
    }

    public Integer getGraphPort() {
        return graphPort;
    }

    public void setGraphPort(Integer graphPort) {
        this.graphPort = graphPort;
    }

    public String getGraphApiVersion() {
        return graphApiVersion;
    }

    public void setGraphApiVersion(String graphApiVersion) {
        this.graphApiVersion = graphApiVersion;
    }

    public String getMessagingPath() {
        return messagingPath;
    }

    public void setMessagingPath(String messagingPath) {
        this.messagingPath = messagingPath;
    }

    public String getGraphProtocol() {
        return graphProtocol;
    }

    public void setGraphProtocol(String graphProtocol) {
        this.graphProtocol = graphProtocol;
    }

    public String getEventsPath() {
        return eventsPath;
    }

    public void setEventsPath(String eventsPath) {
        this.eventsPath = eventsPath;
    }

    public String[] getProfileFields() {
        return profileFields;
    }

    public void setProfileFields(String[] profileFields) {
        this.profileFields = profileFields;
    }
}
