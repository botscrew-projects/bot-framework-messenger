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

package com.botscrew.messengercdk.domain.action;

import com.botscrew.messengercdk.model.outgoing.request.Request;

/**
 * Describes message before sending some message to a user
 */
public class BeforeSendMessage implements MessengerAction {
    private final String token;
    private final Request request;

    public BeforeSendMessage(String token, Request request) {
        this.token = token;
        this.request = request;
    }

    public String getToken() {
        return token;
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "BeforeSendMessage{" +
                "token='" + token + '\'' +
                ", request=" + request +
                '}';
    }
}
