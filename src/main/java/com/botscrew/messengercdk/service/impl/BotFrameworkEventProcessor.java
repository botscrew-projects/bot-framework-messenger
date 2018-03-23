package com.botscrew.messengercdk.service.impl;

import com.botscrew.botframework.container.LocationContainer;
import com.botscrew.botframework.container.PostbackContainer;
import com.botscrew.botframework.container.TextContainer;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.Coordinates;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.service.BotProvider;
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
    private final BotProvider botProvider;

    private final TextContainer textContainer;
    private final PostbackContainer postbackContainer;
    private final LocationContainer locationContainer;

    public BotFrameworkEventProcessor(UserProvider userProvider, TextContainer textContainer,
                                      PostbackContainer postbackContainer, LocationContainer locationContainer) {
    private final List<MessengerInterceptor<PreMessageProcessingAction>> preInterceptors;

    public BotFrameworkEventProcessor(UserProvider userProvider,
                                      BotProvider botProvider,
                                      TextContainer textContainer,
                                      PostbackContainer postbackContainer,
                                      LocationContainer locationContainer,
                                      List<MessengerInterceptor<PreMessageProcessingAction>> preMessagingInterceptors
                                      ) {
        processors = new EnumMap<>(EventType.class);
        processors.put(EventType.TEXT, this::processText);
        processors.put(EventType.QUICK_REPLY, this::processQuickReply);
        processors.put(EventType.POSTBACK, this::processPostback);
        processors.put(EventType.LOCATION, this::processLocation);

        this.userProvider = userProvider;
        this.botProvider = botProvider;
        this.textContainer = textContainer;
        this.postbackContainer = postbackContainer;
        this.locationContainer = locationContainer;
        this.preInterceptors = preMessagingInterceptors;
    }

    @Override
    public void process(EventType type, Messaging messaging) {
        preInterceptors.forEach(i -> i.onAction(new PreMessageProcessingAction(messaging)));

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
