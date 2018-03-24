package com.botscrew.messengercdk.model.incomming;

import com.botscrew.messengercdk.model.outgoing.button.Button;
import com.botscrew.messengercdk.model.outgoing.template.TemplateElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Payload {
	private String url;
	private Coordinates coordinates;
	@JsonProperty("template_type")
	private String templateType;
	private List<Button> buttons;
}
