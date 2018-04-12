package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Referral {
    @JsonProperty("ref")
    private String value;
    private String source;
    private String type;
    @JsonProperty("ad_id")
    private Long adId;
    @JsonProperty("referer_uri")
    private String refererUri;
}
