package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.outgoing.Request;

import java.util.concurrent.ScheduledFuture;

public interface TokenizedSender {
    void send(String token, Request request);

    ScheduledFuture send(String token, Request request, Integer delayMillis);
}