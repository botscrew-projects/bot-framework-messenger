package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.outgoing.GenericElement;
import com.botscrew.messenger.cdk.model.outgoing.QuickReply;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

public interface TokenizedSender {


    void send(String token, MessengerUser recipient, String text);
    ScheduledFuture send(String token, MessengerUser recipient, String text, Integer delayMillis);

    void send(String token, MessengerUser recipient, String text, List<QuickReply> quickReplies);
    ScheduledFuture send(String token, MessengerUser recipient, String text, List<QuickReply> quickReplies, Integer delayMillis);

    void send(String token, MessengerUser recipient, List<GenericElement> elements);
    ScheduledFuture send(String token, MessengerUser recipient, List<GenericElement> elements, Integer delayMillis);

    void send(String token, MessengerUser recipient, List<GenericElement> elements, List<QuickReply> quickReplies);
    ScheduledFuture send(String token, MessengerUser recipient, List<GenericElement> elements, List<QuickReply> quickReplies, Integer delayMillis);

}