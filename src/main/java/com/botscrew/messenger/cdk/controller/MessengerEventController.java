package com.botscrew.messenger.cdk.controller;

import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.model.incomming.Report;
import com.botscrew.messenger.cdk.service.ReportHandler;
import com.botscrew.messenger.cdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messenger/events")
@RequiredArgsConstructor
public class MessengerEventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessengerProperties.class);

    private final ReportHandler handler;
    private final SubscriptionReviewer subscriptionReviewer;

    @RequestMapping(method = RequestMethod.POST)
    private void index(@RequestBody(required = false) Report report) {
        handler.handle(report);
    }

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<String> index(@RequestParam(name = "hub.challenge") String challenge,
                                         @RequestParam(name = "hub.verify_token") String verifyToken) {
        LOGGER.info("Subscription event with challenge: {} and verify token: {}", challenge, verifyToken);
        return subscriptionReviewer.review(challenge, verifyToken);
    }
}
