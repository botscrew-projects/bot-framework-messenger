package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.model.outgoing.request.Request;

public class BeforeSendMessage implements MessengerAction {
    private final String token;
    private final Request request;

    public BeforeSendMessage(String token, Request request) {
        this.token = token;
        this.request = request;
    }

    public String getToken() {
        return token;
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "BeforeSendMessage{" +
                "token='" + token + '\'' +
                ", request=" + request +
                '}';
    }
}
