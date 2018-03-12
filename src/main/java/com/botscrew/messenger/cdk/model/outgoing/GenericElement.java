package com.botscrew.messenger.cdk.model.outgoing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Getter
@Setter
@Builder
public class GenericElement {
    private String title;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageUrl;
    @Singular
    private List<Button> buttons;
}
