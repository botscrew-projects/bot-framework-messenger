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

package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.incomming.Profile;
import com.botscrew.messengercdk.model.incomming.webhook.WebHookResponse;
import com.botscrew.messengercdk.model.outgoing.element.WebHook;
import com.botscrew.messengercdk.model.outgoing.element.button.GetStartedButton;
import com.botscrew.messengercdk.model.outgoing.profile.Greeting;
import com.botscrew.messengercdk.model.outgoing.profile.menu.PersistentMenu;

import java.util.List;

/**
 * Abstraction which describes basic actions on Facebook Messenger API
 */
public interface Messenger {

    Profile getProfile(Long chatId);

    Profile getProfile(Long chatId, String token);

    void setGetStartedButton(GetStartedButton button);

    void setGetStartedButton(GetStartedButton button, String token);

    void setPersistentMenu(PersistentMenu menu);

    void setPersistentMenu(PersistentMenu menu, String token);

    boolean removePersistentMenu();

    boolean removePersistentMenu(String token);

    void setGreeting(Greeting greeting);

    void setGreeting(Greeting greeting, String token);

    boolean removeGreeting();

    boolean removeGreeting(String token);

    List<String> getWhitelistedDomains();

    List<String> getWhitelistedDomains(String token);

    void addWhitelistedDomain(String domain);

    void addWhitelistedDomain(String domain, String token);

    boolean removeWhitelistedDomains();

    boolean removeWhitelistedDomains(String token);

    void setWhitelistedDomains(List<String> domains);

    void setWhitelistedDomains(List<String> domains, String token);

    WebHookResponse getWebHooks();

    WebHookResponse getWebHooks(String appId, String appAccessToken);

    Boolean setWebHook(WebHook webHook);

    Boolean setWebHook(String callbackUrl, List<String> fields);
}
