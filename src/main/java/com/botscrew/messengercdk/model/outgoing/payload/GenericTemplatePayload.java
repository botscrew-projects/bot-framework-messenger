package com.botscrew.messengercdk.model.outgoing.payload;

import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericTemplatePayload extends TemplatePayload {
    private Boolean sharable;
    private List<TemplateElement> elements;
    @JsonProperty("image_aspect_ratio")
    private String imageAspectRatio;

    public GenericTemplatePayload(List<TemplateElement> elements) {
        super("generic");
        this.elements = elements;
    }
}
