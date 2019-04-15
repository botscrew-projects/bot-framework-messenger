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

package com.botscrew.messengercdk.model.outgoing.payload;

import com.botscrew.messengercdk.model.outgoing.element.TemplateElement;
import com.botscrew.messengercdk.model.outgoing.element.button.Button;
import com.botscrew.messengercdk.model.outgoing.enums.TopElementStyle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListTemplatePayload extends TemplatePayload {
    private List<TemplateElement> elements;
    private List<Button> buttons;

    @JsonProperty("top_element_style")
    private TopElementStyle topElementStyle;

    public ListTemplatePayload(List<TemplateElement> elements, List<Button> buttons, TopElementStyle topElementStyle) {
        super("list");
        this.elements = elements;
        this.buttons = buttons;
        this.topElementStyle = topElementStyle;
    }
}
