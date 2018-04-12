package com.botscrew.messengercdk.model.outgoing.element.media;

public class ImageElement extends MediaElement {
    private static final String TYPE = "image";

    public ImageElement() {
        super(TYPE);
    }

    public ImageElement(String url) {
        super(TYPE);
        setUrl(url);
    }

    public ImageElement(Long id) {
        super(TYPE);
        setAttachmentId(id);
    }
}
