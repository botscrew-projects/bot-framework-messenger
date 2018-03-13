package com.botscrew.messenger.cdk.model.outgoing.request;

import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.incomming.UserInfo;

public abstract class RequestBuilder<T> {
    UserInfo user;

    public T recipient(MessengerUser user) {
        this.user = new UserInfo(user.getChatId());
        return (T) this;
    }

    public Request build() {
        Request request = new Request();
        request.setRecipient(user);
        return request;
    }
}
