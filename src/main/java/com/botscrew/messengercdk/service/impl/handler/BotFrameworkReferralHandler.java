package com.botscrew.messengercdk.service.impl.handler;

import com.botscrew.botframework.container.ReferralContainer;
import com.botscrew.botframework.domain.argument.ArgumentType;
import com.botscrew.botframework.domain.argument.kit.ArgumentKit;
import com.botscrew.botframework.domain.argument.kit.SimpleArgumentKit;
import com.botscrew.botframework.domain.argument.wrapper.SimpleArgumentWrapper;
import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.model.incomming.Referral;
import com.botscrew.messengercdk.service.EventHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BotFrameworkReferralHandler implements EventHandler {
    private final ReferralContainer referralContainer;

    @Override
    public EventType getHandlingEventType() {
        return EventType.REFERRAL;
    }

    @Override
    public void handle(MessengerUser messengerUser, Messaging messaging) {
        Referral referral = messaging.getReferral() != null
                ? messaging.getReferral()
                : messaging.getPostback().getReferral();

        ArgumentKit kit = new SimpleArgumentKit();
        kit.put(ArgumentType.ORIGINAL_RESPONSE, new SimpleArgumentWrapper(referral));
        if (messaging.getPostback() != null) {
            kit.put(ArgumentType.POSTBACK,
                    new SimpleArgumentWrapper(messaging.getPostback().getPayload()));
        }
        referralContainer.process(messengerUser, referral.getValue(), kit);
    }
}
