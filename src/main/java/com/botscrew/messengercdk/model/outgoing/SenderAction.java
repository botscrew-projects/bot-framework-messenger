package com.botscrew.messengercdk.model.outgoing;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SenderAction {
    TYPING_ON,
    TYPING_OFF,
    MARK_SEEN;

    @JsonValue
    public String toString() {
        return this.name().toLowerCase();
    }
}
