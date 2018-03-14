package com.botscrew.messengercdk.model.outgoing.button;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Button {

    private String title;
    private String type;

    public Button(String type, String title) {
        this.type = type;
        this.title = title;
    }
}
