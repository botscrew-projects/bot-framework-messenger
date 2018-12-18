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

package com.botscrew.messengercdk.model.incomming;

import com.botscrew.messengercdk.model.incomming.Attachment;
import com.botscrew.messengercdk.model.incomming.Postback;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Message {

    @JsonProperty("is_echo")
    private boolean isEcho;
    private String mid;
    private Integer seq;
    private String text;
    private List<Attachment> attachments;
    @JsonProperty("quick_reply")
    private Postback quickReply;
    @JsonProperty("app_id")
    private Long appId;
    private String metadata;
}