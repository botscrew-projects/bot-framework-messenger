package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Coordinates {

	@JsonProperty("long")
	private Double longitude;
	@JsonProperty("lat")
	private Double latitude;
	
}
