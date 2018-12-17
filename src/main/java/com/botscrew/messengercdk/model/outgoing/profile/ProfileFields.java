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

package com.botscrew.messengercdk.model.outgoing.profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileFields {
    public static final String PERSISTENT_MENU = "persistent_menu";
    public static final String GET_STARTED = "get_started";
    public static final String GREETING = "greeting";
    public static final String WHITELISTED_DOMAINS = "whitelisted_domains";
    public static final String ACCOUNT_LINKING_URL = "account_linking_url";

    private final List<String> fields;

    public ProfileFields() {
        this.fields = new ArrayList<>();
    }

    public com.botscrew.messengercdk.model.outgoing.profile.ProfileFields withPersistentMenu() {
        return with(PERSISTENT_MENU);
    }

    public com.botscrew.messengercdk.model.outgoing.profile.ProfileFields withGetStarted() {
        return with(GET_STARTED);
    }

    public com.botscrew.messengercdk.model.outgoing.profile.ProfileFields withGreeting() {
        return with(GREETING);
    }

    public com.botscrew.messengercdk.model.outgoing.profile.ProfileFields withWhitelistedDomains() {
        return with(WHITELISTED_DOMAINS);
    }

    public com.botscrew.messengercdk.model.outgoing.profile.ProfileFields withAccountLinkingUrl() {
        return with(ACCOUNT_LINKING_URL);
    }

    public com.botscrew.messengercdk.model.outgoing.profile.ProfileFields with(String field) {
        fields.add(field);
        return this;
    }

    public List<String> getFields() {
        return fields;
    }
}
