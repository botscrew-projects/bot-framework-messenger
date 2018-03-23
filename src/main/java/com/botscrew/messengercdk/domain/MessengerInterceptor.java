package com.botscrew.messengercdk.domain;

public interface MessengerInterceptor<E extends MessengerAction> {

    boolean onAction(E e);
}
