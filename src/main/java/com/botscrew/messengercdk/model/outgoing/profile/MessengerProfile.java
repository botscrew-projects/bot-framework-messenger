package com.botscrew.messengercdk.model.outgoing.profile;

import com.botscrew.messengercdk.model.outgoing.element.button.GetStartedButton;
import com.botscrew.messengercdk.model.outgoing.profile.menu.PersistentMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessengerProfile {
    @JsonProperty("get_started")
    private GetStartedButton getStartedButton;
    @JsonProperty("persistent_menu")
    private List<PersistentMenu> persistentMenus;
    @JsonProperty("greeting")
    private List<Greeting> greetings;
    @JsonProperty("whitelisted_domains")
    private List<String> whitelistedDomains;

    public GetStartedButton getGetStartedButton() {
        return getStartedButton;
    }

    public void setGetStartedButton(GetStartedButton getStartedButton) {
        this.getStartedButton = getStartedButton;
    }

    public List<PersistentMenu> getPersistentMenus() {
        return persistentMenus;
    }

    public void setPersistentMenus(List<PersistentMenu> persistentMenus) {
        this.persistentMenus = persistentMenus;
    }

    public List<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }

    public List<String> getWhitelistedDomains() {
        return whitelistedDomains;
    }

    public void setWhitelistedDomains(List<String> whitelistedDomains) {
        this.whitelistedDomains = whitelistedDomains;
    }
}
