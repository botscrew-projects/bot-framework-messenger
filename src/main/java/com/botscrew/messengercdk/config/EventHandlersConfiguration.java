package com.botscrew.messengercdk.config;

import com.botscrew.botframework.container.LocationContainer;
import com.botscrew.botframework.container.PostbackContainer;
import com.botscrew.botframework.container.TextContainer;
import com.botscrew.messengercdk.service.impl.handler.BotFrameworkLocationEventHandler;
import com.botscrew.messengercdk.service.impl.handler.BotFrameworkPostbackEventHandler;
import com.botscrew.messengercdk.service.impl.handler.BotFrameworkQuickReplyEventHandler;
import com.botscrew.messengercdk.service.impl.handler.BotFrameworkTextEventHandler;
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
}
