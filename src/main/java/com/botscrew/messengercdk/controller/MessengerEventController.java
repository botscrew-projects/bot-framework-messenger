package com.botscrew.messengercdk.controller;

import com.botscrew.messengercdk.model.incomming.Report;
import com.botscrew.messengercdk.service.ReportHandler;
import com.botscrew.messengercdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("${facebook.messenger.events-path:/messenger/events}")
@RequiredArgsConstructor
public class MessengerEventController {
    private final ReportHandler handler;
    private final SubscriptionReviewer subscriptionReviewer;

    @PostMapping
    public void index(@RequestBody Report report) {
        handler.handle(report);
    }

    @GetMapping
    public ResponseEntity<String> index(@RequestParam(name = "hub.challenge") String challenge,
                                        @RequestParam(name = "hub.verify_token") String verifyToken) {
        log.info("Subscription event with challenge: {} and verify token: {}", challenge, verifyToken);
        return subscriptionReviewer.review(challenge, verifyToken);
    }
}
