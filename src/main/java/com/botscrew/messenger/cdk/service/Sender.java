package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.outgoing.template.TemplateElement;
import com.botscrew.messenger.cdk.model.outgoing.QuickReply;
import com.botscrew.messenger.cdk.model.outgoing.request.Request;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

public interface Sender {
    void send(MessengerUser recipient, String text);
    ScheduledFuture send(MessengerUser recipient, String text, Integer delayMillis);

    void send(MessengerUser recipient, String text, List<QuickReply> quickReplies);
    ScheduledFuture send(MessengerUser recipient, String text, List<QuickReply> quickReplies, Integer delayMillis);

    void send(MessengerUser recipient, List<TemplateElement> elements);
    ScheduledFuture send(MessengerUser recipient, List<TemplateElement> elements, Integer delayMillis);

    void send(MessengerUser recipient, List<TemplateElement> elements, List<QuickReply> quickReplies);
    ScheduledFuture send(MessengerUser recipient, List<TemplateElement> elements, List<QuickReply> quickReplies, Integer delayMillis);

    void send(Request request);
    ScheduledFuture send(Request request, Integer delayMillis);
}
