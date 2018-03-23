package com.botscrew.messenger.cdk.domain;

public interface MessengerInterceptor<E extends MessengerAction> {

    boolean onAction(E e);
}
