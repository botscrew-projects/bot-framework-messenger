package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.outgoing.QuickReply;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.service.Sender;
import com.botscrew.messengercdk.service.TokenizedSender;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
public class DefaultSender implements Sender {

    private final TokenizedSender tokenizedSender;
    private final MessengerProperties properties;

    public void send(MessengerUser recipient, String text) {
        tokenizedSender.send(properties.getAccessToken(), recipient, text);
    }

    @Override
    public ScheduledFuture send(MessengerUser recipient, String text, Integer delayMillis) {
        return tokenizedSender.send(properties.getAccessToken(), recipient, text, delayMillis);
    }

    @Override
    public void send(MessengerUser recipient, String text, List<QuickReply> quickReplies) {
        tokenizedSender.send(properties.getAccessToken(), recipient, text, quickReplies);
    }

    @Override
    public ScheduledFuture send(MessengerUser recipient, String text, List<QuickReply> quickReplies, Integer delayMillis) {
        return tokenizedSender.send(properties.getAccessToken(), recipient, text, quickReplies, delayMillis);
    }

    @Override
    public void send(MessengerUser recipient, List<TemplateElement> elements) {
        tokenizedSender.send(properties.getAccessToken(), recipient, elements);
    }

    @Override
    public ScheduledFuture send(MessengerUser recipient, List<TemplateElement> elements, Integer delayMillis) {
        return tokenizedSender.send(properties.getAccessToken(), recipient, elements, delayMillis);
    }

    @Override
    public void send(MessengerUser recipient, List<TemplateElement> elements, List<QuickReply> quickReplies) {
        tokenizedSender.send(properties.getAccessToken(), recipient, elements, quickReplies);
    }

    @Override
    public ScheduledFuture send(MessengerUser recipient, List<TemplateElement> elements, List<QuickReply> quickReplies, Integer delayMillis) {
        return tokenizedSender.send(properties.getAccessToken(), recipient, elements, quickReplies, delayMillis);
    }

    @Override
    public void send(Request request) {
        tokenizedSender.send(properties.getAccessToken(), request);
    }

    @Override
    public ScheduledFuture send(Request request, Integer delayMillis) {
        return tokenizedSender.send(properties.getAccessToken(), request, delayMillis);
    }
}
