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
    private final List<String> fields;

    public ProfileFields() {
        this.fields = new ArrayList<>();
    }

    public ProfileFields withPersistentMenu() {
        fields.add("persistent_menu");
        return this;
    }

    public ProfileFields withGetStarted() {
        fields.add("get_started");
        return this;
    }

    public ProfileFields withGreeting() {
        fields.add("greeting");
        return this;
    }

    public ProfileFields withWhitelistedDomains() {
        fields.add("whitelisted_domains");
        return this;
    }

    public ProfileFields withAccountLinkingUrl() {
        fields.add("account_linking_url");
        return this;
    }

    public List<String> getFields() {
        return fields;
    }
}
