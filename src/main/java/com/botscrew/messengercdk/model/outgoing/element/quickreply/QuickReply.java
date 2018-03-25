package com.botscrew.messengercdk.model.outgoing.element.quickreply;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class QuickReply {
	@JsonProperty(value = "content_type")
    private String contentType;

	protected QuickReply(String contentType) {
		this.contentType = contentType;
	}

}