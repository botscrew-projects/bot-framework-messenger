package com.botscrew.messengercdk.model.outgoing.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentPayload {
    @JsonProperty("attachment_id")
    private Long attachmentId;
    @JsonProperty("is_reusable")
    private Boolean isReusable;
    private String url;

    public AttachmentPayload(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public AttachmentPayload(String url) {
        this.url = url;
    }

    public AttachmentPayload(String url, Boolean isReusable) {
        this.isReusable = isReusable;
        this.url = url;
    }
}
