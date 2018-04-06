package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.model.incomming.Response;
import com.botscrew.messengercdk.model.outgoing.request.Request;

public class AfterSendMessage implements MessengerAction {
    private final String token;
    private final Request request;
    private final Response response;

    public AfterSendMessage(String token, Request request, Response response) {
        this.token = token;
        this.request = request;
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "AfterSendMessage{" +
                "token='" + token + '\'' +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
