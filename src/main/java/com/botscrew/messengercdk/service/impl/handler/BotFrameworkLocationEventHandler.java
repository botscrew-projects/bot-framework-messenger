package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.LocationContainer;
import com.botscrew.botframework.domain.argument.ArgumentType;
import com.botscrew.botframework.domain.argument.kit.ArgumentKit;
import com.botscrew.botframework.domain.argument.kit.SimpleArgumentKit;
import com.botscrew.botframework.domain.argument.wrapper.SimpleArgumentWrapper;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.Coordinates;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventHandler;

public class BotFrameworkLocationEventHandler implements EventHandler {
    private final LocationContainer locationContainer;

    public BotFrameworkLocationEventHandler(LocationContainer locationContainer) {
        this.locationContainer = locationContainer;
    }

    @Override
    public EventType getHandlingEventType() {
        return EventType.LOCATION;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        Coordinates coordinates = messaging.getMessage().getAttachments().get(0).getPayload().getCoordinates();
        ArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.COORDINATES, new SimpleArgumentWrapper(coordinates));

        locationContainer.process(messengerUser, kit);
    }
}
