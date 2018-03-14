package com.botscrew.messenger.cdk.model.incomming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Payload {
	private String url;
	private Coordinates coordinates;
}
