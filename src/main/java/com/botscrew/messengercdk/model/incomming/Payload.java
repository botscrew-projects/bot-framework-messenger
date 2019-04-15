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

import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Payload {
    private String url;
    private Coordinates coordinates;
    @JsonProperty("template_type")
    private String templateType;
    private Boolean sharable;
    private List<Button> buttons;
    private List<TemplateElement> elements;
    @JsonProperty("image_aspect_ratio")
    private String imageAspectRatio;
    @JsonProperty("attachment_id")
    private Long attachmentId;
    @JsonProperty("is_reusable")
    private Boolean isReusable;
}
