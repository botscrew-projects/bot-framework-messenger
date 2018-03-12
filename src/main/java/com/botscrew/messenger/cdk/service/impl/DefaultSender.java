package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.model.outgoing.Request;
import com.botscrew.messenger.cdk.service.Sender;
import com.botscrew.messenger.cdk.service.TokenizedSender;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultSender implements Sender {

    private final TokenizedSender tokenizedSender;
    private final MessengerProperties messengerProperties;

    @Override
    public void send(Request request) {
        tokenizedSender.send(messengerProperties.getAccessToken(), request);
    }

    @Override
    public void send(Request request, Integer delayMillis) {
        tokenizedSender.send(messengerProperties.getAccessToken(), request, delayMillis);
    }
}
