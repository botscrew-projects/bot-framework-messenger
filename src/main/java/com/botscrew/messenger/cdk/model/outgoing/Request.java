package com.botscrew.messenger.cdk.model.outgoing;

import com.botscrew.messenger.cdk.model.incomming.UserInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request implements com.botscrew.framework.flow.sender.Message{

    @JsonProperty("messaging_type")
    private MessagingType messagingType;
    private UserInfo recipient;
    private Message message;
}