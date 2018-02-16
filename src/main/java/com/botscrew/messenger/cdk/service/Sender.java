package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.outgoing.GenericElement;
import com.botscrew.messenger.cdk.model.outgoing.QuickReply;

import java.util.List;

public interface Sender {
    void send(MessengerUser recipient, String text);

    void send(MessengerUser recipient, String text, List<QuickReply> quickReplies);

    void send(MessengerUser recipient, List<GenericElement> elements);

    void send(MessengerUser recipient, List<GenericElement> elements, List<QuickReply> quickReplies);
}
