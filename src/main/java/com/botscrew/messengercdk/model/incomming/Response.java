package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {
    @JsonProperty("recipient_id")
    private Long recipientId;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("attachment_id")
    private Long attachmentId;

}
