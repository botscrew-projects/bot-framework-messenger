package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.config.MessagingUrlBuilder;
import com.botscrew.messenger.cdk.exception.SendAPIException;
import com.botscrew.messenger.cdk.model.outgoing.Request;
import com.botscrew.messenger.cdk.service.TokenizedSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@RequiredArgsConstructor
public class TokenizedSenderImpl implements TokenizedSender {
    private final RestTemplate restTemplate;
    private final MessagingUrlBuilder messagingUrl;
    private final ThreadPoolTaskScheduler scheduler;

    @Override
    public void send(String token, Request request) {
        post(token, request);
    }

    @Override
    public ScheduledFuture send(String token, Request request, Integer delayMillis) {
        Date when = addToDate(new Date(), Calendar.MILLISECOND, delayMillis);
        return scheduler.schedule(() -> send(token, request), when);
    }

    private void post(String token, Object message) {
        log.debug("Posting message: \n{}", message);
        try {
            restTemplate.postForObject(messagingUrl.getMessagingUrlWith(token), message, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new SendAPIException(e.getResponseBodyAsString());
        }
    }

    private Date addToDate(Date date, int calendarField, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarField, amount);
        return calendar.getTime();
    }
}
