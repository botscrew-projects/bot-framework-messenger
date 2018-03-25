package com.botscrew.messengercdk.model.outgoing.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private String text;

    public Message() {}

    public Message(String text) {
        this.text = text;
    }
}
