package com.botscrew.messengercdk.model.outgoing.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TemplatePayload {

    @JsonProperty("template_type")
    private String templateType;

    public TemplatePayload(String templateType) {
        this.templateType = templateType;
    }
}
