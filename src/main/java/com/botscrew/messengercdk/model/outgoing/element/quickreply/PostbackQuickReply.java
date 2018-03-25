package com.botscrew.messengercdk.model.outgoing.element.quickreply;

public class PostbackQuickReply extends QuickReply {
    private String title;
    private String payload;

    public PostbackQuickReply(String title, String payload) {
        super("text");
        this.title = title;
        this.payload = payload;
    }

    public String getTitle() {
        return title;
    }

    public String getPayload() {
        return payload;
    }
}
