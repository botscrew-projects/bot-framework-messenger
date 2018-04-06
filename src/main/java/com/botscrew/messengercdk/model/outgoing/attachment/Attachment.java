package com.botscrew.messengercdk.model.outgoing.attachment;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Attachment {
    private Type type;

    Attachment(Type type) {
        this.type = type;
    }

    public enum Type {
        AUDIO,
        VIDEO,
        IMAGE,
        FILE,
        TEMPLATE;

        @JsonValue
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
