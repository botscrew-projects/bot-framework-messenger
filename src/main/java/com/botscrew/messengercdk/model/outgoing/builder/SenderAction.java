package com.botscrew.messengercdk.model.outgoing.builder;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.request.SenderActionRequest;

public class SenderAction {

    private SenderAction() {
    }

    public static SenderActionRequest typingOn(MessengerUser user) {
        return new SenderActionRequest(new UserInfo(user.getChatId()),
                com.botscrew.messengercdk.model.outgoing.enums.SenderAction.TYPING_ON);
    }

    public static SenderActionRequest typingOff(MessengerUser user) {
        return new SenderActionRequest(new UserInfo(user.getChatId()),
                com.botscrew.messengercdk.model.outgoing.enums.SenderAction.TYPING_OFF);
    }

    public static SenderActionRequest markSeen(MessengerUser user) {
        return new SenderActionRequest(new UserInfo(user.getChatId()),
                com.botscrew.messengercdk.model.outgoing.enums.SenderAction.MARK_SEEN);
    }
}
