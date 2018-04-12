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
