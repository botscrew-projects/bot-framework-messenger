package com.botscrew.messenger.cdk.model.incomming;

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
	private List<MessageElement> elements;
	private List<Button> buttons;
}
