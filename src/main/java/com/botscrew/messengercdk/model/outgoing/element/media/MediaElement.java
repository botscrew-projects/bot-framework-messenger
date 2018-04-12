package com.botscrew.messengercdk.model.outgoing.element.media;

import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class MediaElement {
    @JsonProperty("media_type")
    private String type;
    private String url;
    @JsonProperty("attachment_id")
    private Long attachmentId;
    private List<Button> buttons;

    public MediaElement(String type) {
        this.type = type;
    }
}
