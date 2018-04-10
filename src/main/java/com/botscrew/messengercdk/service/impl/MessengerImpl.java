package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.exception.MessengerCDKException;
import com.botscrew.messengercdk.model.incomming.Profile;
import com.botscrew.messengercdk.model.outgoing.element.button.GetStartedButton;
import com.botscrew.messengercdk.model.outgoing.profile.Greeting;
import com.botscrew.messengercdk.model.outgoing.profile.MessengerProfile;
import com.botscrew.messengercdk.model.outgoing.profile.menu.PersistentMenu;
import com.botscrew.messengercdk.service.Messenger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class MessengerImpl implements Messenger {
    private final RestTemplate restTemplate;
    private final MessengerProperties properties;

    public MessengerImpl(RestTemplate restTemplate, MessengerProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public Profile getProfile(Long chatId) {
        String profileUrl = properties.getProfileUrl(chatId.toString());
        return tryToGetProfile(profileUrl);
    }

    @Override
    public Profile getProfile(Long chatId, String token) {
        String profileUrl = properties.getProfileUrl(chatId.toString(), token);
        return tryToGetProfile(profileUrl);
    }

    private Profile tryToGetProfile(String profileUrl) {
        try {
            return restTemplate.getForObject(profileUrl, Profile.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new MessengerCDKException(e.getResponseBodyAsString());
        }
    }

    @Override
    public void setGetStartedButton(GetStartedButton button) {
        setGetStartedButton(button, properties.getAccessToken());
    }

    @Override
    public void setGetStartedButton(GetStartedButton button, String token) {
        MessengerProfile profile = new MessengerProfile();
        profile.setGetStartedButton(button);

        trySetMessengerProfile(profile, token);
    }

    @Override
    public void setPersistentMenu(PersistentMenu menu) {
        setPersistentMenu(menu, properties.getAccessToken());
    }

    @Override
    public void setPersistentMenu(PersistentMenu menu, String token) {
        MessengerProfile profile = new MessengerProfile();
        profile.setPersistentMenus(Collections.singletonList(menu));

        trySetMessengerProfile(profile, token);
    }

    @Override
    public void setGreeting(Greeting greeting) {
        setGreeting(greeting, properties.getAccessToken());
    }

    @Override
    public void setGreeting(Greeting greeting, String token) {
        MessengerProfile profile = new MessengerProfile();
        profile.setGreetings(Collections.singletonList(greeting));

        trySetMessengerProfile(profile, token);
    }

    @Override
    public void setWhitelistedDomains(List<String> domains) {
        setWhitelistedDomains(domains, this.properties.getAccessToken());
    }

    @Override
    public void setWhitelistedDomains(List<String> domains, String token) {
        MessengerProfile profile = new MessengerProfile();
        profile.setWhitelistedDomains(domains);

        trySetMessengerProfile(profile, token);
    }

    private void trySetMessengerProfile(MessengerProfile profile, String token) {
        String url = properties.getPageProfileUrl(token);
        try {
            restTemplate.postForObject(url, profile, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new MessengerCDKException(e.getResponseBodyAsString());
        }
    }
}
