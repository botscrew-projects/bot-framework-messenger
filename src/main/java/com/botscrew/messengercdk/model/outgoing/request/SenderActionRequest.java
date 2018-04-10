package com.botscrew.messengercdk.model.outgoing.request;

import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.enums.SenderAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SenderActionRequest extends Request {
    @JsonProperty("sender_action")
    private SenderAction senderAction;

    public SenderActionRequest() {
    }

    public SenderActionRequest(UserInfo recipient, SenderAction senderAction) {
        super(recipient);
        this.senderAction = senderAction;
    }

    public SenderAction getSenderAction() {
        return senderAction;
    }

    public void setSenderAction(SenderAction senderAction) {
        this.senderAction = senderAction;
    }

    @Override
    public String toString() {
        return "SenderActionRequest{" +
                "senderAction=" + senderAction +
                '}';
    }
}
