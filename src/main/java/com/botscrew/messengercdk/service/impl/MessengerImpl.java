/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.exception.GraphAPIException;
import com.botscrew.messengercdk.exception.MessengerCDKException;
import com.botscrew.messengercdk.model.incomming.GraphResponse;
import com.botscrew.messengercdk.model.incomming.Profile;
import com.botscrew.messengercdk.model.incomming.webhook.WebHookResponse;
import com.botscrew.messengercdk.model.outgoing.element.WebHook;
import com.botscrew.messengercdk.model.outgoing.element.button.GetStartedButton;
import com.botscrew.messengercdk.model.outgoing.profile.Greeting;
import com.botscrew.messengercdk.model.outgoing.profile.MessengerProfile;
import com.botscrew.messengercdk.model.outgoing.profile.ProfileFields;
import com.botscrew.messengercdk.model.outgoing.profile.menu.PersistentMenu;
import com.botscrew.messengercdk.service.Messenger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Implementation of {@link Messenger} used by default
 */
public class MessengerImpl implements Messenger {
    private final RestTemplate restTemplate;
    private final MessengerProperties properties;
    private final ObjectMapper objectMapper;

    public MessengerImpl(RestTemplate restTemplate,
                         MessengerProperties properties,
                         ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.properties = properties;
        this.objectMapper = objectMapper;
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
    public boolean removePersistentMenu() {
        return removePersistentMenu(properties.getAccessToken());
    }

    @Override
    public boolean removePersistentMenu(String token) {
        String url = properties.getPageProfileUrl(token);
        ProfileFields fields = new ProfileFields().withPersistentMenu();
        return tryToRemoveProfileFields(url, fields);
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
    public boolean removeGreeting() {
        return removeGreeting(properties.getAccessToken());
    }

    @Override
    public boolean removeGreeting(String token) {
        String url = properties.getPageProfileUrl(token);
        ProfileFields fields = new ProfileFields().withGreeting();
        return tryToRemoveProfileFields(url, fields);
    }

    private boolean tryToRemoveProfileFields(String url, ProfileFields fields) {
        try {
            HttpEntity<ProfileFields> entity = new HttpEntity<>(fields);
            ResponseEntity<GraphResponse> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, GraphResponse.class);
            return response.getStatusCode().is2xxSuccessful() && response.getBody().isSuccessful();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new MessengerCDKException(e.getResponseBodyAsString());
        }
    }

    @Override
    public List<String> getWhitelistedDomains() {
        return getWhitelistedDomains(properties.getAccessToken());
    }

    @Override
    public List<String> getWhitelistedDomains(String token) {
        Map<String, String> params = new HashMap<>();
        params.put("fields", ProfileFields.WHITELISTED_DOMAINS);
        String url = properties.getPageProfileUrl(token, params);

        try {
            String json = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode data = jsonNode.get("data");
            Iterator<JsonNode> elements = data.elements();
            if (elements.hasNext()) {
                JsonNode first = elements.next();
                JsonNode fields = first.get(ProfileFields.WHITELISTED_DOMAINS);
                String[] domains = objectMapper.readValue(fields.toString(), String[].class);
                return Arrays.asList(domains);
            }
            else {
                return new ArrayList<>();
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new MessengerCDKException(e.getResponseBodyAsString());
        } catch (IOException e) {
            throw new MessengerCDKException(e.getMessage());
        }
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

    @Override
    public void addWhitelistedDomain(String domain) {
        addWhitelistedDomain(domain, properties.getAccessToken());
    }

    @Override
    public void addWhitelistedDomain(String domain, String token) {
        List<String> whitelistedDomains = getWhitelistedDomains(token);
        if (!whitelistedDomains.contains(domain)) {
            whitelistedDomains = new ArrayList<>(whitelistedDomains);
            whitelistedDomains.add(domain);
        }
        setWhitelistedDomains(whitelistedDomains, token);
    }

    @Override
    public boolean removeWhitelistedDomains() {
        return removeWhitelistedDomains(properties.getAccessToken());
    }

    @Override
    public boolean removeWhitelistedDomains(String token) {
        String url = properties.getPageProfileUrl(token);
        ProfileFields fields = new ProfileFields().withWhitelistedDomains();
        return tryToRemoveProfileFields(url, fields);
    }

    private void trySetMessengerProfile(MessengerProfile profile, String token) {
        String url = properties.getPageProfileUrl(token);
        try {
            restTemplate.postForObject(url, profile, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new MessengerCDKException(e.getResponseBodyAsString());
        }
    }

    @Override
    public WebHookResponse getWebHooks() {
        return getWebHooks(properties.getAppId(), properties.getAppAccessToken());
    }

    @Override
    public WebHookResponse getWebHooks(Long appId, String appAccessToken) {
        String graphApiSubscriptionsUrl = properties.getGraphApiSubscriptionsUrl(appId, appAccessToken);
        try {
            return restTemplate.getForObject(graphApiSubscriptionsUrl, WebHookResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new GraphAPIException(e.getResponseBodyAsString());
        }
    }

    @Override
    public Boolean setWebHook(WebHook webHook) {
        if (webHook.getAppId() == null) {
            webHook.setAppId(properties.getAppId());
        }
        if (webHook.getAccessToken() == null || webHook.getAccessToken().isEmpty()) {
            webHook.setAccessToken(properties.getAccessToken());
        }
        if (webHook.getObject() == null || webHook.getObject().isEmpty()) {
            webHook.setObject("page");
        }

        String url = properties.getGraphApiSubscriptionsUrl(webHook.getAppId());

        try {
            GraphResponse response = restTemplate.postForObject(url, webHook, GraphResponse.class);
            return response.isSuccessful();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new GraphAPIException(e.getResponseBodyAsString());
        }
    }


    @Override
    public Boolean setWebHook(String callbackUrl, List<String> fields) {
        WebHook webHook = WebHook.builder()
                .object("page")
                .appId(properties.getAppId())
                .accessToken(properties.getAppAccessToken())
                .callbackUrl(callbackUrl)
                .verifyToken(properties.getVerifyToken())
                .fields(fields)
                .build();

        return setWebHook(webHook);

    }
}
