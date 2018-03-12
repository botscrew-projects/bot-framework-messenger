package com.botscrew.messenger.cdk.controller;

import com.botscrew.messenger.cdk.model.incomming.Report;
import com.botscrew.messenger.cdk.service.ReportHandler;
import com.botscrew.messenger.cdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/messenger/events")
@RequiredArgsConstructor
public class MessengerEventController {
    private final ReportHandler handler;
    private final SubscriptionReviewer subscriptionReviewer;

    @RequestMapping(method = RequestMethod.POST)
    public void index(@RequestBody(required = false) Report report) {
        handler.handle(report);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> index(@RequestParam(name = "hub.challenge") String challenge,
                                         @RequestParam(name = "hub.verify_token") String verifyToken) {
        log.info("Subscription event with challenge: {} and verify token: {}", challenge, verifyToken);
        return subscriptionReviewer.review(challenge, verifyToken);
    }
}
