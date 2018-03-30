package com.botscrew.messengercdk.model.outgoing.profile;

public class Greeting {
    private String locale;
    private String text;

    public Greeting(String text) {
        this.locale = "default";
        this.text = text;
    }

    public Greeting(String locale, String text) {
        this.locale = locale;
        this.text = text;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
