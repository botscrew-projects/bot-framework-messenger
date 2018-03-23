package com.botscrew.messenger.cdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Den Boyko
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class MessageElement {
    private String title;
    @JsonProperty("image_url")
    private String imageUrl;
    private String subtitle;
    private List<Button> buttons;
}
