package com.botscrew.messenger.cdk.model.outgoing.request;

import com.botscrew.messenger.cdk.model.outgoing.QuickReplyMessage;
import com.botscrew.messenger.cdk.model.outgoing.QuickReply;

import java.util.ArrayList;
import java.util.List;

public class QuickReplyBuilder extends RequestBuilder<QuickReplyBuilder> {
    protected String text;
    protected List<QuickReply> quickReplies = new ArrayList<>();

    public QuickReplyBuilder text(String text) {
        this.text = text;
        return this;
    }

    public QuickReplyBuilder quickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
        return this;
    }

    public QuickReplyBuilder quickReply(QuickReply quickReply) {
        quickReplies.add(quickReply);
        return this;
    }

    public QuickReplyBuilder postback(String title, String payload) {
        return quickReply(QuickReply.postback(title, payload));
    }

    public QuickReplyBuilder location() {
        return quickReply(QuickReply.location());
    }

    @Override
    public Request build() {
        Request request = super.build();
        request.setMessage(new QuickReplyMessage(text, quickReplies));
        return request;
    }
}
