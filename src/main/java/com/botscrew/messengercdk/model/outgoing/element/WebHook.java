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
        public static String MESSAGES = "messages";
        public static String POSTBACKS = "messaging_postbacks";
        public static String OPTINS = "messaging_optins";
        public static String DELIVERIES = "message_deliveries";
        public static String READS = "message_reads";
        public static String PAYMENTS = "messaging_payments";
        public static String PRE_CHECKOUTS = "messaging_pre_checkouts";
        public static String CHECKOUT_UPDATES = "messaging_checkout_updates";
        public static String ACCOUNT_LINKING = "messaging_account_linking";
        public static String REFERRALS = "messaging_referrals";
        public static String ECHOES = "message_echoes";
        public static String GAME_PLAYS = "messaging_game_plays";
        public static String STANDBY = "standby";
        public static String HANDOVERS = "messaging_handovers";
        public static String POLICY_ENFOCEMENT = "messaging_policy_enforcement";
    }
}
