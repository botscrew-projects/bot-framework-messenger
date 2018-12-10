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

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.botframework.domain.user.Platform;

public class MessengerBot implements Bot {
    private String accessToken;
    private Long pageId;

    public MessengerBot(Long pageId, String accessToken) {
        this.pageId = pageId;
        this.accessToken = accessToken;
    }

    @Override
    public Platform getPlatform() {
        return Platform.FB_MESSENGER;
    }

    public Long getPageId() {
        return pageId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
