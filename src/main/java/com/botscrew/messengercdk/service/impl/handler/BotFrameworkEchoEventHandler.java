package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.EchoContainer;
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
public class BotFrameworkEchoEventHandler implements EventHandler {
    private final EchoContainer echoContainer;

    @Override
    public EventType getHandlingEventType() {
        return EventType.ECHO;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        ArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.ORIGINAL_RESPONSE, new SimpleArgumentWrapper(messaging.getMessage()));

        echoContainer.process(messengerUser, kit);
    }
}
