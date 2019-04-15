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

import com.botscrew.messengercdk.model.incomming.Response;
import com.botscrew.messengercdk.model.outgoing.request.Request;

/**
 * Describes action after sending some message to a user
 */
public class AfterSendMessage implements MessengerAction {
    private final String token;
    private final Request request;
    private final Response response;

    public AfterSendMessage(String token, Request request, Response response) {
        this.token = token;
        this.request = request;
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "AfterSendMessage{" +
                "token='" + token + '\'' +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
