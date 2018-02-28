package com.botscrew.messenger.cdk.service;


import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.incomming.Messaging;

public interface EchoProcessor {

    void processEcho(MessengerUser user, Messaging messaging);
}
