package com.botscrew.messengercdk.model.outgoing.element;

import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Getter
@Setter
@Builder
public class TemplateElement {
    private String title;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageUrl;
    @Singular
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Button> buttons;
}
