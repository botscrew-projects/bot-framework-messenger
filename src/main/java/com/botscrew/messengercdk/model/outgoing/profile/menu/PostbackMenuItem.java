package com.botscrew.messengercdk.model.outgoing.profile.menu;

public class PostbackMenuItem extends MenuItem {
    private String payload;

    public PostbackMenuItem(String title, String payload) {
        super("postback", title);
        this.payload = payload;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String payload;
        private String title;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public PostbackMenuItem build() {
            return new PostbackMenuItem(title, payload);
        }
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PostbackMenuItem{" +
                "title='" + getTitle() + '\'' +
                "payload='" + payload + '\'' +
                '}';
    }
}
