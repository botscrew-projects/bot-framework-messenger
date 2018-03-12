package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.outgoing.Request;

public interface Sender {
    void send(Request request);

    void send(Request request, Integer delayMillis);
}
