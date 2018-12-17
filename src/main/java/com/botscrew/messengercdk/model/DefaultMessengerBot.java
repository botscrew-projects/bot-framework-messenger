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

package com.botscrew.messengercdk.model;

import com.botscrew.messengercdk.model.MessengerBot;

/**
 * @author Den Boyko
 * @version 1.0
 */
public class DefaultMessengerBot implements MessengerBot {
    private String accessToken;
    private Long appId;

    public DefaultMessengerBot(Long appId, String accessToken) {
        this.appId = appId;
        this.accessToken = accessToken;
    }

    @Override
    public Long getPageId() {
        return appId;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }
}
