package com.botscrew.messengercdk.model.outgoing.request;

import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.message.Message;
import com.botscrew.messengercdk.model.outgoing.MessagingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "PlainRequestBuilder")
public class Request implements com.botscrew.botframework.sender.Message {

    @JsonProperty("messaging_type")
    private MessagingType messagingType;
    private UserInfo recipient;
    private Message message;
}
