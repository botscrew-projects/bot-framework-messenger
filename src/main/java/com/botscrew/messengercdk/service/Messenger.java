package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.incomming.Profile;
import com.botscrew.messengercdk.model.outgoing.element.button.GetStartedButton;
import com.botscrew.messengercdk.model.outgoing.profile.Greeting;
import com.botscrew.messengercdk.model.outgoing.profile.menu.PersistentMenu;

import java.util.List;

public interface Messenger {

    Profile getProfile(Long chatId);

    Profile getProfile(Long chatId, String token);

    void setGetStartedButton(GetStartedButton button);

    void setGetStartedButton(GetStartedButton button, String token);

    void setPersistentMenu(PersistentMenu menu);

    void setPersistentMenu(PersistentMenu menu, String token);

    void setGreeting(Greeting greeting);

    void setGreeting(Greeting greeting, String token);

    void setWhitelistedDomains(List<String> domains);

    void setWhitelistedDomains(List<String> domains, String token);
}
