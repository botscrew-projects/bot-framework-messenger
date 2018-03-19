package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.container.LocationContainer;
import com.botscrew.botframework.container.PostbackContainer;
import com.botscrew.botframework.container.TextContainer;
import com.botscrew.botframework.domain.ArgumentKit;
import com.botscrew.botframework.domain.SimpleArgumentKit;
import com.botscrew.botframework.domain.SimpleArgumentWrapper;
import com.botscrew.botframework.model.ArgumentType;
import com.botscrew.botframework.model.GeoCoordinates;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.Coordinates;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.EventProcessor;
import com.botscrew.messengercdk.service.UserProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
public class BotFrameworkEventProcessor implements EventProcessor {
    private final Map<EventType, BiConsumer<MessengerUser, Messaging>> processors;

    private final UserProvider userProvider;

    private final TextContainer textContainer;
    private final PostbackContainer postbackContainer;
    private final LocationContainer locationContainer;

    public BotFrameworkEventProcessor(UserProvider userProvider, TextContainer textContainer,
                                      PostbackContainer postbackContainer, LocationContainer locationContainer) {
        processors = new EnumMap<>(EventType.class);
        processors.put(EventType.TEXT, this::processText);
        processors.put(EventType.QUICK_REPLY, this::processQuickReply);
        processors.put(EventType.POSTBACK, this::processPostback);
        processors.put(EventType.LOCATION, this::processLocation);

        this.userProvider = userProvider;
        this.textContainer = textContainer;
        this.postbackContainer = postbackContainer;
        this.locationContainer = locationContainer;
    }

    @Override
    public void process(EventType type, Messaging messaging) {
        if (type == EventType.UNDEFINED) {
            log.warn("Messaging with type: UNDEFINED, {}", messaging);
            return;
        }

        try {
            MessengerUser user = userProvider.getByChatId(messaging.getSender().getId());
            processors.get(type).accept(user, messaging);
        }
        catch (Exception e) {
            log.error("Error during processing messaging: " + messaging.toString(), e);
        }
    }

    private void processText(MessengerUser user, Messaging messaging) {
        ArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.TEXT, new SimpleArgumentWrapper(messaging.getMessage().getText()));
        textContainer.process(user, kit);
    }

    private void processQuickReply(MessengerUser user, Messaging messaging) {
        postbackContainer.process(user, messaging.getMessage().getQuickReply().getPayload(), new SimpleArgumentKit());
    }

    private void processPostback(MessengerUser user, Messaging messaging) {
        postbackContainer.process(user, messaging.getPostback().getPayload(), new SimpleArgumentKit());
    }

    private void processLocation(MessengerUser user, Messaging messaging) {
        Coordinates coordinates = messaging.getMessage().getAttachments().get(0).getPayload().getCoordinates();
        ArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.COORDINATES, new SimpleArgumentWrapper(coordinates));

        locationContainer.process(user, kit);
    }
}
