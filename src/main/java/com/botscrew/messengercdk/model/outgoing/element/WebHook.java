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

package com.botscrew.messengercdk.model.outgoing.element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WebHook {

    private String object = "page";
    @JsonProperty("callback_url")
    private String callbackUrl;
    private List<String> fields;
    @JsonProperty("verify_token")
    private String verifyToken;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonIgnore
    private String appId;

    public static class Field {
        private Field() {}

        public static final String MESSAGES = "messages";
        public static final String POSTBACKS = "messaging_postbacks";
        public static final String OPTINS = "messaging_optins";
        public static final String DELIVERIES = "message_deliveries";
        public static final String READS = "message_reads";
        public static final String PAYMENTS = "messaging_payments";
        public static final String PRE_CHECKOUTS = "messaging_pre_checkouts";
        public static final String CHECKOUT_UPDATES = "messaging_checkout_updates";
        public static final String ACCOUNT_LINKING = "messaging_account_linking";
        public static final String REFERRALS = "messaging_referrals";
        public static final String ECHOES = "message_echoes";
        public static final String GAME_PLAYS = "messaging_game_plays";
        public static final String STANDBY = "standby";
        public static final String HANDOVERS = "messaging_handovers";
        public static final String POLICY_ENFOCEMENT = "messaging_policy_enforcement";
    }
}
