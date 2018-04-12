package com.botscrew.messengercdk.model.outgoing.element.button;

import com.botscrew.messengercdk.model.outgoing.message.Message;
import com.botscrew.messengercdk.model.outgoing.request.MessageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShareButton extends Button {
    private static final String TYPE = "element_share";

    @JsonProperty("share_contents")
    private Message shareContent;

    public ShareButton() {
        super(TYPE, null);
    }

    public ShareButton(Message message) {
        super(TYPE, null);
        this.shareContent = message;
    }

    public ShareButton(MessageRequest request) {
        super(TYPE, null);
        this.shareContent = request.getMessage();
    }

    public void setShareContent(Message share) {
        this.shareContent = share;
    }

    public Message getShareContent() {
        return shareContent;
    }

    public void setShareContent(MessageRequest request) {
        this.shareContent = request.getMessage();
    }
}
