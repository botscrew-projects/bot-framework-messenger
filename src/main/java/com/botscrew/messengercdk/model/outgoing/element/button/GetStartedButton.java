package com.botscrew.messengercdk.model.outgoing.element.button;

public class GetStartedButton {

    private String payload;

    public GetStartedButton(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
