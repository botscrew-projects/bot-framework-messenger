package com.botscrew.messenger.cdk.config;

import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class MessagingUrlBuilder {
    private static final String HTTPS = "https";

    private static final String ACCESS_TOKEN_PLACEHOLDER = "<AT>";
    
    private final MessengerProperties properties;
    
    private String templateMessagingUrl;
    private String messagingUrl;
    
    @Autowired
    public MessagingUrlBuilder(MessengerProperties properties) {
        this.properties = properties;
    }
    
    @PostConstruct
    public void buildMessagingUrls() {
        String path = "/me/messages";
        String query = "access_token=" + ACCESS_TOKEN_PLACEHOLDER;

        try {
            URI templateUri = new URI(HTTPS, null, properties.getGraphHost(), properties.getGraphPort(),
                                        path, query, null);

            templateMessagingUrl = templateUri.toURL().toString();
        } catch (URISyntaxException | MalformedURLException e) {
            log.error("Problem with configuring messaging url", e);
            throw new SystemException("Problem with configuring messaging url", e);
        }
    }

    public String getMessagingUrlWith(String token) {
        return templateMessagingUrl.replace(ACCESS_TOKEN_PLACEHOLDER, token);
    }
}
