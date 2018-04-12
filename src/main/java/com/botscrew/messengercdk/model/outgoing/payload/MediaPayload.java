package com.botscrew.messengercdk.model.outgoing.payload;

import com.botscrew.messengercdk.model.outgoing.element.media.MediaElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MediaPayload extends TemplatePayload {
    private List<MediaElement> elements;

    public MediaPayload(List<MediaElement> elements) {
        super("media");
        this.elements = elements;
    }
}
