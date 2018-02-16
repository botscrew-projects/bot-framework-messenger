package com.botscrew.messenger.cdk.model.outgoing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Attachment {

    private String type;

    public Attachment(String type) {
        this.type = type;
    }
}
