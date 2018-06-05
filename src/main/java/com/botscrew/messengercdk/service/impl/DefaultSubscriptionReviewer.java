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

package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.service.SubscriptionReviewer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * Implementation of {@link SubscriptionReviewer} used by default
 */
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
