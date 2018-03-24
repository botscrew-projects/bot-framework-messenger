package com.botscrew.messengercdk.test;

import com.botscrew.botframework.annotation.ChatEventsProcessor;
import com.botscrew.botframework.annotation.Text;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.UserInfo;
import com.botscrew.messengercdk.model.outgoing.QuickReply;
import com.botscrew.messengercdk.model.outgoing.button.PostbackButton;
import com.botscrew.messengercdk.model.outgoing.button.WebButton;
import com.botscrew.messengercdk.model.outgoing.request.Request;
import com.botscrew.messengercdk.model.outgoing.template.TemplateAttachment;
import com.botscrew.messengercdk.model.outgoing.template.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.template.generic.GenericTemplateMessage;
import com.botscrew.messengercdk.model.outgoing.template.generic.GenericTemplatePayload;
import com.botscrew.messengercdk.model.outgoing.template.list.TopElementStyle;
import com.botscrew.messengercdk.service.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Slf4j
@ChatEventsProcessor
public class TestService {

    @Autowired
    private Sender sender;

    @Text
    public void text(MessengerUser user, @Text String text) {
        if (text == null || text.equals("a")) {
            QuickReply postback = QuickReply.postback("Title", "Postback");
            TemplateElement build = TemplateElement.builder()
                    .title("Title")
                    .subtitle("subtitle")
                    .imageUrl("http://lostfilm-online.ru/uploads/posts/2015-01/1422172418_lost.jpg")
                    .button(new WebButton("Title", "https://google.com"))
                    .build();

            TemplateAttachment templateAttachment = new TemplateAttachment(new GenericTemplatePayload(Collections.singletonList(build)));
            Request request = new Request();
            request.setMessage(new GenericTemplateMessage(Collections.singletonList(postback), templateAttachment));
            request.setRecipient(new UserInfo(user.getChatId()));
            sender.send(request);
        } else if (text.equals("b")) {
            sendQuickReplyMessage(user);
        } else if (text.equals("c")) {
            sendGenericTemplate(user);
        } else if (text.equals("d")) {
            sendListTemplate(user);
        }
    }

    public void sendQuickReplyMessage(MessengerUser user) {
        Request request = Request.quickReply()
                .text("Some text")
                .postback("Red", "RED_POSTBACK")
                .postback("Blue", "BLUE_POSTBACK")
                .location()
                .recipient(user)
                .build();
        sender.send(request);
    }

    public void sendGenericTemplate(MessengerUser user) {
        Request request = Request.genericTemplate()
                .element(
                        TemplateElement.builder()
                                .imageUrl("http://lostfilm-online.ru/uploads/posts/2015-01/1422172418_lost.jpg")
                                .title("TITLE")
                                .subtitle("subtitle")
                                .button(new WebButton("google.com", "https://google.com"))
                                .build())
                .recipient(user)
                .build();
        sender.send(request);
    }

    public void sendListTemplate(MessengerUser user) {
        TemplateElement listTemplateElement = TemplateElement.builder()
                .title("TITLE")
                .subtitle("SUBTITLE")
                .imageUrl("http://lostfilm-online.ru/uploads/posts/2015-01/1422172418_lost.jpg")
                .button(new PostbackButton("Help", "HELP_POSTBACK"))
                .build();

        Request request = Request.listTemplate()
                .element(listTemplateElement)
                .element(listTemplateElement)
                .element(listTemplateElement)
                .button(new PostbackButton("Read more", "READ_MORE_POSTBACK"))
                .topElementStyle(TopElementStyle.LARGE)
                .recipient(user)
                .build();

        sender.send(request);
    }
}
