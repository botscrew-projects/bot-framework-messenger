package com.botscrew.messengercdk.model.incomming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MessagingBundle {

	private String id;
	private Long time;
	private List<Messaging> messaging;

}
