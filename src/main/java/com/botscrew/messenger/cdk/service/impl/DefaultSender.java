package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.MessengerBot;
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
    private final String accessToken;

    @Override
    public void send(MessengerUser recipient, String text) {
        tokenizedSender.send(accessToken, recipient, text);
    }

    @Override
    public void send(MessengerUser recipient, String text, List<QuickReply> quickReplies) {
        tokenizedSender.send(accessToken, recipient, text, quickReplies);
    }

    @Override
    public void send(MessengerUser recipient, List<GenericElement> elements) {
        tokenizedSender.send(accessToken, recipient, elements);
    }

    @Override
    public void send(MessengerUser recipient, List<GenericElement> elements, List<QuickReply> quickReplies) {
        tokenizedSender.send(accessToken, recipient, elements, quickReplies);
    }
}
