package com.botscrew.messengercdk.model.outgoing.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TopElementStyle {
    LARGE,
    COMPACT;

    @JsonValue
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}