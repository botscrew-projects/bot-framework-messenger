package com.botscrew.messengercdk.model.outgoing.element.quickreply;

public class EmailQuickReply extends QuickReply {
    public EmailQuickReply() {
        super("user_email");
    }

    public EmailQuickReply(String imageUrl) {
        super("user_email");
        this.setImageUrl(imageUrl);
    }
}
