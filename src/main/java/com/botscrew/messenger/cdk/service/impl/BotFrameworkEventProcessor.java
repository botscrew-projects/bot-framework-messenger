package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.framework.flow.container.LocationContainer;
import com.botscrew.framework.flow.container.PostbackContainer;
import com.botscrew.framework.flow.container.TextContainer;
import com.botscrew.framework.flow.model.GeoCoordinates;
import com.botscrew.messenger.cdk.model.MessengerBot;
import com.botscrew.messenger.cdk.model.MessengerUser;
import com.botscrew.messenger.cdk.model.incomming.Coordinates;
import com.botscrew.messenger.cdk.model.incomming.EventType;
import com.botscrew.messenger.cdk.model.incomming.Messaging;
import com.botscrew.messenger.cdk.service.BotProvider;
import com.botscrew.messenger.cdk.service.EchoProcessor;
import com.botscrew.messenger.cdk.service.EventProcessor;
import com.botscrew.messenger.cdk.service.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BotFrameworkEventProcessor implements EventProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotFrameworkEventProcessor.class);

    private final Map<EventType, BiConsumer<MessengerUser, Messaging>> processors;

    private final UserProvider userProvider;
    private final BotProvider botProvider;

    private final TextContainer textContainer;
    private final PostbackContainer postbackContainer;
    private final LocationContainer locationContainer;

    public BotFrameworkEventProcessor(UserProvider userProvider,
                                      BotProvider botProvider,
                                      TextContainer textContainer,
                                      PostbackContainer postbackContainer,
                                      LocationContainer locationContainer,
                                      EchoProcessor echoProcessor) {
        processors = new EnumMap<>(EventType.class);
        processors.put(EventType.TEXT, this::processText);
        processors.put(EventType.QUICK_REPLY, this::processQuickReply);
        processors.put(EventType.POSTBACK, this::processPostback);
        processors.put(EventType.LOCATION, this::processLocation);
        processors.put(EventType.ECHO, echoProcessor::processEcho);


        this.userProvider = userProvider;
        this.botProvider = botProvider;
        this.textContainer = textContainer;
        this.postbackContainer = postbackContainer;
        this.locationContainer = locationContainer;
    }

    @Override
    public void process(EventType type, Messaging messaging) {
        if (type == EventType.UNDEFINED) {
            LOGGER.warn("Messaging with type: UNDEFINED, {0}", messaging);
            return;
        }
        try {
            MessengerBot messengerBot = botProvider.findById(messaging.getRecipient().getId());
            MessengerUser user = userProvider.getByChatIdAndBotId(messaging.getSender().getId(), messengerBot);
            processors.get(type).accept(user, messaging);
        }
        catch (Exception e) {
            LOGGER.error("Error during processing messaging: " + messaging.toString(), e);
        }
    }

    private void processText(MessengerUser user, Messaging messaging) {
        textContainer.processText(messaging.getMessage().getText(), user);
    }

    private void processQuickReply(MessengerUser user, Messaging messaging) {
        postbackContainer.processPostback(messaging.getMessage().getQuickReply().getPayload(), user);
    }

    private void processPostback(MessengerUser user, Messaging messaging) {
        postbackContainer.processPostback(messaging.getPostback().getPayload(), user);
    }

    private void processLocation(MessengerUser user, Messaging messaging) {
        Coordinates coordinates = messaging.getMessage().getAttachments().get(0).getPayload().getCoordinates();
        GeoCoordinates geo = new GeoCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
        locationContainer.processLocation(geo, user);
    }
}
