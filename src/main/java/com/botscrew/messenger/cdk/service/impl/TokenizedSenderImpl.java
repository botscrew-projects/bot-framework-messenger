package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.exception.SendAPIException;
import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.incomming.UserInfo;
import com.botscrew.messenger.cdk.model.outgoing.*;
import com.botscrew.messenger.cdk.service.TokenizedSender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class TokenizedSenderImpl implements TokenizedSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReportHandler.class);

    private final RestTemplate restTemplate;
    private final MessengerProperties properties;

    @Override
    public void send(String token, MessengerUser recipient, String text) {
        Request message = Request.builder()
                .message(new Message(text))
                .recipient(new UserInfo(recipient.getChatId()))
                .build();

        post(message);
    }

    @Override
    public void send(String token, MessengerUser recipient, String text, List<QuickReply> quickReplies) {
        Request message = Request.builder()
                .message(new QuickRepliesMessage(text, quickReplies))
                .recipient(new UserInfo(recipient.getChatId()))
                .build();

        post(message);
    }

    @Override
    public void send(String token, MessengerUser recipient, List<GenericElement> elements) {
        send(token, recipient, elements, null);
    }

    @Override
    public void send(String token, MessengerUser recipient, List<GenericElement> elements, List<QuickReply> quickReplies) {
        TemplateAttachment templateAttachment = new TemplateAttachment(new TemplatePayload(elements));
        Request message = Request.builder()
                .message(new GenericTemplateMessage(quickReplies, templateAttachment))
                .recipient(new UserInfo(recipient.getChatId()))
                .build();

        post(message);
    }

    private void post(Object message) {
        LOGGER.debug("Posting message: \n{0}", message);
        try {
            restTemplate.postForObject(properties.messagingUrl(), message, String.class);
        }catch (HttpClientErrorException|HttpServerErrorException e) {
            throw new SendAPIException(e.getResponseBodyAsString());
        }
    }
}
