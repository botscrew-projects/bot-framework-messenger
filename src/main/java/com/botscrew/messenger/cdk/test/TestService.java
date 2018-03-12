package com.botscrew.messenger.cdk.test;

import com.botscrew.botframework.annotation.ChatEventsProcessor;
import com.botscrew.botframework.annotation.Text;
import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.outgoing.GenericElement;
import com.botscrew.messenger.cdk.model.outgoing.QuickReply;
import com.botscrew.messenger.cdk.model.outgoing.WebButton;
import com.botscrew.messenger.cdk.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@ChatEventsProcessor
public class TestService {

    @Autowired
    private Sender sender;

    @Text
    public void text(MessengerUser user, String text) {
        QuickReply postback = QuickReply.postback("Title", "Postback");
        GenericElement build = GenericElement.builder()
                .title("Title")
                .subtitle("subtitle")
                .imageUrl("http://lostfilm-online.ru/uploads/posts/2015-01/1422172418_lost.jpg")
                .buttons(Collections.singletonList(new WebButton("Title", "https://google.com")))
                .build();

        sender.send(user, Collections.singletonList(build), Collections.singletonList(postback));
    }
}
