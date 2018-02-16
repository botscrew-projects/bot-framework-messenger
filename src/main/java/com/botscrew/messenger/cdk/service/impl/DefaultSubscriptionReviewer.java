package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@RequiredArgsConstructor
public class DefaultSubscriptionReviewer implements SubscriptionReviewer {

    private final MessengerProperties messengerProperties;

    @Override
    public ResponseEntity<String> review(String challenge, String verifyToken) {
        if (Objects.equals(messengerProperties.getVerifyToken(), verifyToken)) {
            return ResponseEntity.ok(challenge);
        }
        else return ResponseEntity.badRequest().body("Invalid verify token");
    }
}
