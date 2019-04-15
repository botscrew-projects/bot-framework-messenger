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

import java.util.List;

public class PersistentMenu {

    private String locale;
    @JsonProperty("call_to_actions")
    private List<MenuItem> menuItems;
    @JsonProperty("composer_input_disabled")
    private boolean composerInputDisabled;

    public PersistentMenu(List<MenuItem> menuItems) {
        this.locale = "default";
        this.menuItems = menuItems;
    }

    public PersistentMenu(String locale, List<MenuItem> menuItems) {
        this.locale = locale;
        this.menuItems = menuItems;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public boolean isComposerInputDisabled() {
        return composerInputDisabled;
    }

    public void setComposerInputDisabled(boolean composerInputDisabled) {
        this.composerInputDisabled = composerInputDisabled;
    }

    @Override
    public String toString() {
        return "PersistentMenu{" +
                "locale='" + locale + '\'' +
                ", menuItems=" + menuItems +
                ", composerInputDisabled=" + composerInputDisabled +
                '}';
    }
}
