package com.botscrew.messengercdk.model.outgoing.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SenderAction {
    TYPING_ON,
    TYPING_OFF,
    MARK_SEEN;

    @JsonValue
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
