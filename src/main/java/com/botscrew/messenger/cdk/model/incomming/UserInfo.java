package com.botscrew.messenger.cdk.model.incomming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserInfo {
    private Long id;

    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }
}