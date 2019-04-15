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

package com.botscrew.messengercdk.model.outgoing.profile.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuItem extends MenuItem {
    @JsonProperty("call_to_actions")
    private List<MenuItem> nestedMenuItems;

    public NestedMenuItem(String title, List<MenuItem> nestedMenuItems) {
        super("nested", title);
        this.nestedMenuItems = nestedMenuItems;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<MenuItem> getNestedMenuItems() {
        return nestedMenuItems;
    }

    public void setNestedMenuItems(List<MenuItem> nestedMenuItems) {
        this.nestedMenuItems = nestedMenuItems;
    }

    @Override
    public String toString() {
        return "NestedMenuItem{" +
                "title=" + getTitle() +
                "nestedMenuItems=" + nestedMenuItems +
                '}';
    }

    public static class Builder {
        private String title;
        private List<MenuItem> nestedMenuItems = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder addMenuItem(MenuItem item) {
            this.nestedMenuItems.add(item);
            return this;
        }

        public Builder menuItems(List<MenuItem> items) {
            this.nestedMenuItems = items;
            return this;
        }

        public NestedMenuItem build() {
            return new NestedMenuItem(title, nestedMenuItems);
        }
    }
}
