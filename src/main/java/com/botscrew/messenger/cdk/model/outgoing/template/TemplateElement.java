package com.botscrew.messenger.cdk.model.outgoing.template;

import com.botscrew.messenger.cdk.model.outgoing.button.Button;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    private List<Button> buttons;
}
