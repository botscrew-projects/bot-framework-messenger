/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.config;

import com.botscrew.botframework.container.*;
import com.botscrew.messengercdk.service.impl.handler.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Configuration
 * Describes default event handlers available inside messenger module
 */
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
