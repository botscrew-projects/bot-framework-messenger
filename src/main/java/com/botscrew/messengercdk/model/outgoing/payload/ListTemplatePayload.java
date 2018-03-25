package com.botscrew.messengercdk.model.outgoing.payload;

import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.style.TopElementStyle;
import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListTemplatePayload extends TemplatePayload {
    private List<TemplateElement> elements;
    private List<Button> buttons;

    @JsonProperty("top_element_style")
    private TopElementStyle topElementStyle;

    public ListTemplatePayload(List<TemplateElement> elements, List<Button> buttons, TopElementStyle topElementStyle) {
        super("list");
        this.elements = elements;
        this.buttons = buttons;
        this.topElementStyle = topElementStyle;
    }
}
