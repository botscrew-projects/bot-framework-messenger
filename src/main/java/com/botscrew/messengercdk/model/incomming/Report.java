package com.botscrew.messengercdk.model.incomming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Report {

    private String object;
    private List<MessagingBundle> entry;

}
