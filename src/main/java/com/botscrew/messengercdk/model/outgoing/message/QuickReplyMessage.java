package com.botscrew.messengercdk.model.outgoing.message;

import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuickReplyMessage extends Message {

    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;

    public QuickReplyMessage(String text, List<QuickReply> quickReplies) {
        super(text);
        this.quickReplies = quickReplies;
    }
}
