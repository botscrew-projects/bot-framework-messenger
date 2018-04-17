package com.botscrew.messengercdk.model.incomming.webhook;

import com.botscrew.messengercdk.model.outgoing.element.WebHook;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WebHookResponse {
    @JsonProperty("data")
    private List<GraphWebHook> webHooks;
}
