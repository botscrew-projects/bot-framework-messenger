package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.TextContainer;
import com.botscrew.botframework.domain.argument.ArgumentType;
import com.botscrew.botframework.domain.argument.kit.SimpleArgumentKit;
import com.botscrew.botframework.domain.argument.wrapper.SimpleArgumentWrapper;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventHandler;

public class BotFrameworkTextEventHandler implements EventHandler {
    private final TextContainer textContainer;

    public BotFrameworkTextEventHandler(TextContainer textContainer) {
        this.textContainer = textContainer;
    }

    @Override
    public EventType getHandlingEventType() {
        return EventType.TEXT;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        String text = messaging.getMessage().getText();

        SimpleArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.TEXT, new SimpleArgumentWrapper(text));

        textContainer.process(messengerUser, kit);
    }
}
