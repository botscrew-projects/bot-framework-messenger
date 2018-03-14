package com.botscrew.messengercdk.model.outgoing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class QuickReply {

	@JsonProperty(value = "content_type")
    private String contentType;

	private String title;
	private String payload;

	public QuickReply() {
	}

	private QuickReply(String contentType) {
		this.contentType = contentType;
	}

	private QuickReply(String contentType, String title, String payload) {
		this.contentType = contentType;
		this.title = title;
		this.payload = payload;
	}

	public static QuickReply postback(String title, String payload) {
		return new QuickReply("text", title, payload);
	}

	public static QuickReply location() {
		return new QuickReply("location");
	}

}