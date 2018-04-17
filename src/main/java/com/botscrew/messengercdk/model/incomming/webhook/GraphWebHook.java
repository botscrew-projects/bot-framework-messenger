package com.botscrew.messengercdk.model.incomming.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GraphWebHook {
    private String object;
    @JsonProperty("callback_url")
    private String callbackUrl;
    private Boolean active;
    private List<GraphField> fields;

}
