package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@RequiredArgsConstructor
public class DefaultSubscriptionReviewer implements SubscriptionReviewer {

    private final MessengerProperties messengerProperties;

    @Override
    public ResponseEntity<String> review(String challenge, String verifyToken) {
        if (Objects.equals(messengerProperties.getVerifyToken(), verifyToken)) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.badRequest().body("Invalid verify token");
        }
    }
}
