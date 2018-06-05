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

package com.botscrew.messengercdk.model.outgoing.enums;

public enum MessagingType {
    /**
     * Message is in response to a received message.
     * This includes promotional and non-promotional messages
     * sent inside the 24-hour standard messaging window or under the 24+1 policy.
     * For example, use this tag to respond if a person asks for
     * a reservation confirmation or an status update.
     */
    RESPONSE,

    /**
     * Message is being sent proactively and is not in response to a received message.
     * This includes promotional and non-promotional messages
     * sent inside the the 24-hour standard messaging window or under the 24+1 policy.
     */
    UPDATE,

    /**
     * Message is non-promotional and is being sent outside the 24-hour standard
     * messaging window with a message tag.
     * The message must match the allowed use case for the tag.
     */
    MESSAGE_TAG,
}
