package com.botscrew.messengercdk.service;

import org.springframework.http.ResponseEntity;

public interface SubscriptionReviewer {
    ResponseEntity<String> review(String challenge, String verifyToken);
}
