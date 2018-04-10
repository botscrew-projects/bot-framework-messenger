package com.botscrew.messengercdk.model.outgoing.request;

import com.botscrew.messengercdk.model.incomming.UserInfo;

public abstract class Request implements com.botscrew.botframework.sender.Message {

    private UserInfo recipient;

    public Request() {
    }

    public Request(UserInfo recipient) {
        this.recipient = recipient;
    }

    public UserInfo getRecipient() {
        return recipient;
    }

    public void setRecipient(UserInfo recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Request{" +
                "recipient=" + recipient +
                '}';
    }
}