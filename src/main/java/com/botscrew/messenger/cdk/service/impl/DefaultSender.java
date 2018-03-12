package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.outgoing.GenericElement;
import com.botscrew.messenger.cdk.model.outgoing.QuickReply;
import com.botscrew.messenger.cdk.service.Sender;
import com.botscrew.messenger.cdk.service.TokenizedSender;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DefaultSender implements Sender {

    private final TokenizedSender tokenizedSender;
    private final MessengerProperties messengerProperties;

    @Override
    public void send(MessengerUser recipient, String text) {
        tokenizedSender.send(messengerProperties.getAccessToken(), recipient, text);
    }

    @Override
    public void send(MessengerUser recipient, String text, List<QuickReply> quickReplies) {
        tokenizedSender.send(messengerProperties.getAccessToken(), recipient, text, quickReplies);
    }

    @Override
    public void send(MessengerUser recipient, List<GenericElement> elements) {
        tokenizedSender.send(messengerProperties.getAccessToken(), recipient, elements);
    }

    @Override
    public void send(MessengerUser recipient, List<GenericElement> elements, List<QuickReply> quickReplies) {
        tokenizedSender.send(messengerProperties.getAccessToken(), recipient, elements, quickReplies);
    }
}
