package com.botscrew.messenger.cdk.model.incomming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Attachment {
    private String type;
    private Payload payload;
    private String title;
    private String url;
}
