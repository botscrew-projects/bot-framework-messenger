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


import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Message;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.model.incomming.Payload;
import com.botscrew.messengercdk.service.EventTypeResolver;

/**
 * Implementation of {@link EventTypeResolver} used by default
 */
public class DefaultEventTypeResolver implements EventTypeResolver {

    @Override
    public EventType resolve(Messaging messaging) {
        if (echoAvailable(messaging)) {
            return EventType.ECHO;
        }
        if (quickReplyAvailable(messaging)) {
            return EventType.QUICK_REPLY;
        }
        if (textAvailable(messaging)) {
            return EventType.TEXT;
        }
        if (referralAvailable(messaging)) {
            return EventType.REFERRAL;
        }
        if (postbackAvailable(messaging)) {
            return EventType.POSTBACK;
        }
        if (readAvailable(messaging)) {
            return EventType.READ;
        }
        if (deliveryAvailable(messaging)) {
            return EventType.DELIVERY;
        }
        if (locationAvailable(messaging)) {
            return EventType.LOCATION;
        }
        if (attachmentsAvailable(messaging)) {
            return EventType.ATTACHMENTS;
        }
        return EventType.UNDEFINED;
    }

    private boolean deliveryAvailable(Messaging messaging) {
        return messaging.getDelivery() != null;
    }

    private boolean readAvailable(Messaging messaging) {
        return messaging.getRead() != null;
    }

    private boolean referralAvailable(Messaging messaging) {
        return messaging.getReferral() != null ||
                (messaging.getPostback() != null && messaging.getPostback().getReferral() != null);
    }

    private boolean echoAvailable(Messaging messaging) {
        return messaging.getMessage() != null && messaging.getMessage().isEcho();
    }

    private boolean textAvailable(Messaging messaging) {
        return messaging.getMessage() != null && isNotNullAndEmpty(messaging.getMessage().getText());
    }

    private boolean postbackAvailable(Messaging messaging) {
        return messaging.getPostback() != null && isNotNullAndEmpty(messaging.getPostback().getPayload());
    }

    private boolean quickReplyAvailable(Messaging messaging) {
        Message m = messaging.getMessage();
        return m != null && m.getQuickReply() != null && isNotNullAndEmpty(m.getQuickReply().getPayload());
    }

    private boolean locationAvailable(Messaging messaging) {
        Message message = messaging.getMessage();
        if (message != null && !message.getAttachments().isEmpty()) {
            Payload payload = message.getAttachments().get(0).getPayload();
            return payload != null && payload.getCoordinates() != null;
        }
        return false;
    }

    private boolean attachmentsAvailable(Messaging messaging) {
        Message message = messaging.getMessage();
        return message != null && !message.getAttachments().isEmpty();
    }

    private boolean isNotNullAndEmpty(String string) {
        return string != null && !string.isEmpty();
    }

}
