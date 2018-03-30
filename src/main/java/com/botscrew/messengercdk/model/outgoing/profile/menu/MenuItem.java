package com.botscrew.messengercdk.model.outgoing.profile.menu;


public abstract class MenuItem {
    private String type;
    private String title;

    public MenuItem(String type) {
        this.type = type;
    }

    public MenuItem(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
