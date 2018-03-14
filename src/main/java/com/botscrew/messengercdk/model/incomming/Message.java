package com.botscrew.messengercdk.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Message {

	private String mid;
	private Integer seq;
	private String text;
	private List<Attachment> attachments;
	@JsonProperty("quick_reply")
	private Postback quickReply;
}
