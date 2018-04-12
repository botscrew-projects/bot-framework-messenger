package com.botscrew.messengercdk.model.outgoing.element.quickreply;

public class PhoneQuickReply extends QuickReply {
    public PhoneQuickReply() {
        super("user_phone_number");
    }

    public PhoneQuickReply(String imageUrl) {
        super("user_phone_number");
        setImageUrl(imageUrl);
    }
}
