package com.botscrew.messengercdk.domain;

import com.botscrew.messengercdk.domain.action.MessengerAction;

public interface MessengerInterceptor<E extends MessengerAction> {

    boolean onAction(E e);
}
