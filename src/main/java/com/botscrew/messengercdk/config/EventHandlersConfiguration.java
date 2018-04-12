package com.botscrew.messengercdk.config;

import com.botscrew.botframework.container.*;
import com.botscrew.messengercdk.service.impl.handler.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHandlersConfiguration {

    @Bean
    @ConditionalOnMissingBean(BotFrameworkLocationEventHandler.class)
    public BotFrameworkLocationEventHandler botFrameworkLocationEventHandler(LocationContainer locationContainer) {
        return new BotFrameworkLocationEventHandler(locationContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkPostbackEventHandler.class)
    public BotFrameworkPostbackEventHandler botFrameworkPostbackEventHandler(PostbackContainer postbackContainer) {
        return new BotFrameworkPostbackEventHandler(postbackContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkQuickReplyEventHandler.class)
    public BotFrameworkQuickReplyEventHandler botFrameworkQuickReplyEventHandler(PostbackContainer postbackContainer) {
        return new BotFrameworkQuickReplyEventHandler(postbackContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkTextEventHandler.class)
    public BotFrameworkTextEventHandler botFrameworkTextEventHandler(TextContainer textContainer) {
        return new BotFrameworkTextEventHandler(textContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkReferralHandler.class)
    public BotFrameworkReferralHandler botFrameworkReferralHandler(ReferralContainer referralContainer) {
        return new BotFrameworkReferralHandler(referralContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkReadEventHandler.class)
    public BotFrameworkReadEventHandler botFrameworkReadEventHandler(ReadContainer readContainer) {
        return new BotFrameworkReadEventHandler(readContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkEchoEventHandler.class)
    public BotFrameworkEchoEventHandler botFrameworkEchoEventHandler(EchoContainer echoContainer) {
        return new BotFrameworkEchoEventHandler(echoContainer);
    }

    @Bean
    @ConditionalOnMissingBean(BotFrameworkDeliveryHandler.class)
    public BotFrameworkDeliveryHandler botFrameworkDeliveryHandler(DeliveryContainer deliveryContainer) {
        return new BotFrameworkDeliveryHandler(deliveryContainer);
    }
}
