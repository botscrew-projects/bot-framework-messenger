package com.botscrew.messenger.cdk.service;

import org.springframework.http.ResponseEntity;

public interface SubscriptionReviewer {
    ResponseEntity<String> review(String challenge, String verifyToken);
}
