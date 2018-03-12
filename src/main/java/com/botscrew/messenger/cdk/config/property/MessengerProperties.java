package com.botscrew.messenger.cdk.config.property;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@ToString
@ConfigurationProperties(prefix = "facebook.messenger")
public class MessengerProperties {
    private String verifyToken;
    private String accessToken;
    private String graphHost;
    private int graphPort;
    private String graphApiVersion;
    private String messagingUrl = null;

    public MessengerProperties() {
        verifyToken = "test";
        graphHost = "graph.facebook.com";
        graphApiVersion = "2.6";
        graphPort = 443;
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
