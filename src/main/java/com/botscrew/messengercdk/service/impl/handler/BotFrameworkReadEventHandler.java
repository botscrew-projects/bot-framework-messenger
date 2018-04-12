package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.ReadContainer;
import com.botscrew.botframework.domain.argument.ArgumentType;
import com.botscrew.botframework.domain.argument.kit.ArgumentKit;
import com.botscrew.botframework.domain.argument.kit.SimpleArgumentKit;
import com.botscrew.botframework.domain.argument.wrapper.SimpleArgumentWrapper;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BotFrameworkReadEventHandler implements EventHandler {
    private final ReadContainer readContainer;

    @Override
    public EventType getHandlingEventType() {
        return EventType.READ;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        ArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.ORIGINAL_RESPONSE, new SimpleArgumentWrapper(messaging.getRead()));

        readContainer.process(messengerUser, kit);
    }
}
