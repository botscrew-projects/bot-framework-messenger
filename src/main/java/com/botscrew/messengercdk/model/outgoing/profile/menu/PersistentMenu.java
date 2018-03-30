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
