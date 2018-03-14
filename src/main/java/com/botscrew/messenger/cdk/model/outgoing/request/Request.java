package com.botscrew.messenger.cdk.model.outgoing.request;

import com.botscrew.messenger.cdk.model.incomming.UserInfo;
import com.botscrew.messenger.cdk.model.outgoing.Message;
import com.botscrew.messenger.cdk.model.outgoing.MessagingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "PlainRequestBuilder")
public class Request {

    @JsonProperty("messaging_type")
    private MessagingType messagingType;
    private UserInfo recipient;
    private Message message;

    public static QuickReplyBuilder quickReply() {
        return new QuickReplyBuilder();
    }

    public static GenericTemplateBuilder genericTemplate() {
        return new GenericTemplateBuilder();
    }

    public static ListTemplateBuilder listTemplate() {
        return new ListTemplateBuilder();
    }

    public static ButtonTemplateBuilder buttonTemplate() {
        return new ButtonTemplateBuilder();
    }
}
