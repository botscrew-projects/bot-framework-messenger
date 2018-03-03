package com.botscrew.messenger.cdk.config.property;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@ToString
@ConfigurationProperties(prefix = "facebook.messenger")
public class MessengerProperties {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

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

    public String messagingUrl() {
        if (messagingUrl == null) {
            buildMessagingUrl();
        }
        return messagingUrl;
    }

    private void buildMessagingUrl() {
        String path = "/me/messages";
        String query = "access_token=" + accessToken;
        try {
            URI uri = new URI(HTTPS, null, graphHost, graphPort, path, query, null);
            messagingUrl = uri.toURL().toString();
        } catch (URISyntaxException | MalformedURLException e) {
            log.error("Problem with configuring messaging url", e);
        }
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
}
