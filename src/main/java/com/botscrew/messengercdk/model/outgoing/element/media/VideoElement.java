package com.botscrew.messengercdk.model.outgoing.element.media;

public class VideoElement extends MediaElement {
    private static final String TYPE = "video";

    public VideoElement() {
        super(TYPE);
    }

    public VideoElement(String url) {
        super(TYPE);
        setUrl(url);
    }

    public VideoElement(Long id) {
        super(TYPE);
        setAttachmentId(id);
    }
}
