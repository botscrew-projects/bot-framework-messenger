package com.botscrew.messengercdk.model.outgoing.element.quickreply;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class QuickReply {
    @JsonProperty(value = "content_type")
    private String contentType;
    @JsonProperty("image_url")
    private String imageUrl;

    protected QuickReply(String contentType) {
        this.contentType = contentType;
    }

}