package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.request.Request;

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
