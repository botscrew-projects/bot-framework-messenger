package com.botscrew.messengercdk.model.outgoing.style;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TopElementStyle {
    LARGE,
    COMPACT;

    @JsonValue
    public String toString() {
        return this.name().toLowerCase();
    }
}