package com.botscrew.messengercdk.model.incomming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Messaging {

    private UserInfo sender;
    private UserInfo recipient;
    private Long timestamp;
    private Message message;
    private Postback postback;
    private Referral referral;
    private Read read;
    private Delivery delivery;

}
