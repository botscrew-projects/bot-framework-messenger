package com.botscrew.messengercdk.model.outgoing.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentPayload {
    private String url;

    /**
     * Only attachments that were uploaded with the is_reusable property
     * set to true can be sent to other message recipients
     */
    @JsonProperty("is_reusable")
    private boolean isReusable;

    @JsonProperty("attachment_id")
    private Long attachmentId;

    public ContentPayload(String url, boolean isReusable) {
        this.url = url;
        this.isReusable = isReusable;
    }

    public ContentPayload(Long attachmentId) {
        this.attachmentId = attachmentId;
    }
}
