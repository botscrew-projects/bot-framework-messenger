/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.controller;

import com.botscrew.messengercdk.model.incomming.Report;
import com.botscrew.messengercdk.service.ReportHandler;
import com.botscrew.messengercdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint for Facebook Messenger events
 * Supports both verification and event requests.
 * Url can be modified via {@link com.botscrew.messengercdk.config.property.MessengerProperties}
 * or can be default.
 */
@Slf4j
@RestController
@RequestMapping("${facebook.messenger.events-path:/messenger/events}")
@RequiredArgsConstructor
public class MessengerEventController {
    private final ReportHandler handler;
    private final SubscriptionReviewer subscriptionReviewer;

    /**
     * Endpoint for Facebook Messenger events
     * @param report Domain object which describes structure of Facebook Messenger events
     */
    @PostMapping
    public void index(@RequestBody Report report) {
        handler.handle(report);
    }

    /**
     * Endpoint for subscribing webhook, verifying token
     * @param challenge Value that need to be returned if verifying is ok
     * @param verifyToken String value added when subsribing a webhook
     * @return status of the verification
     */
    @GetMapping
    public ResponseEntity<String> index(@RequestParam(name = "hub.challenge") String challenge,
                                        @RequestParam(name = "hub.verify_token") String verifyToken) {
        log.info("Subscription event with challenge: {} and verify token: {}", challenge, verifyToken);
        return subscriptionReviewer.review(challenge, verifyToken);
    }
}
