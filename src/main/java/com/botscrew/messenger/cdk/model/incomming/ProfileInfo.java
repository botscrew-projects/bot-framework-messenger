package com.botscrew.messenger.cdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileInfo {
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("profile_pic")
	private String profilePic;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("locale")
	private String locale;
	@JsonProperty("timezone")
	private Integer timezone;
}
