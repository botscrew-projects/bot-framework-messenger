package com.botscrew.messenger.cdk.config.property;

import com.botscrew.messenger.cdk.util.URL;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ToString
@ConfigurationProperties(prefix = "facebook.messenger")
public class MessengerProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessengerProperties.class);

    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    private static final String ACCESS_TOKEN_PARAM = "access_token";

    private String verifyToken;
    private String accessToken;
    private String graphHost;
    private int graphPort;
    private String graphApiVersion;
    private String messagingUrl = null;

    private URL.Builder messagingUrlBuilder = null;
    private String defaultMessagingUrl = null;

    public MessengerProperties() {
        verifyToken = "test";
        graphHost = "graph.facebook.com";
        graphApiVersion = "2.6";
        graphPort = 443;
    }

    public String getMessagingUrl() {
        if (messagingUrlBuilder == null) {
            createMessagingUrlBuilder();
            createDefaultMessagingUrl();
        }
        return defaultMessagingUrl;
    }

    public String getMessagingUrl(String token) {
        if (messagingUrlBuilder == null) {
            createMessagingUrlBuilder();
        }
        return messagingUrlBuilder
                .param(ACCESS_TOKEN_PARAM, token)
                .build()
                .getValue();
    }

    private void createMessagingUrlBuilder() {
        messagingUrlBuilder = new URL.Builder()
                .protocol(HTTPS)
                .host(graphHost)
                .path("/me/messages");
    }

    private void createDefaultMessagingUrl() {
        defaultMessagingUrl = messagingUrlBuilder
                .param(ACCESS_TOKEN_PARAM, accessToken)
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

    public String getGraphApiVersion() {
        return graphApiVersion;
    }

    public void setGraphApiVersion(String graphApiVersion) {
        this.graphApiVersion = graphApiVersion;
    }

    public int getGraphPort() {
        return graphPort;
    }
}
