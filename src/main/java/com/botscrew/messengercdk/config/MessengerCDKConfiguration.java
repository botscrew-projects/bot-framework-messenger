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

import com.botscrew.botframework.domain.user.Platform;
import com.botscrew.botframework.sender.PlatformSender;
import com.botscrew.messengercdk.config.property.ExecutorProperties;
import com.botscrew.messengercdk.config.property.HandlerTaskExecutorProperties;
import com.botscrew.messengercdk.config.property.MessengerProperties;
import com.botscrew.messengercdk.config.property.SenderTaskExecutorProperties;
import com.botscrew.messengercdk.controller.MessengerEventController;
import com.botscrew.messengercdk.domain.MessengerInterceptor;
import com.botscrew.messengercdk.domain.action.AfterSendMessage;
import com.botscrew.messengercdk.domain.action.BeforeSendMessage;
import com.botscrew.messengercdk.domain.action.GetEvent;
import com.botscrew.messengercdk.domain.action.ProcessedEvent;
import com.botscrew.messengercdk.service.*;
import com.botscrew.messengercdk.service.impl.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main Spring configuration for messenger module
 * Imports {@link EventHandlersConfiguration}
 */
@Configuration
@EnableConfigurationProperties(value = {
        MessengerProperties.class,
        HandlerTaskExecutorProperties.class,
        SenderTaskExecutorProperties.class

})
@Import(EventHandlersConfiguration.class)
public class MessengerCDKConfiguration {

    @Bean
    @ConditionalOnMissingBean(Messenger.class)
    public Messenger messenger(RestTemplate restTemplate,
                               MessengerProperties messengerProperties,
                               ObjectMapper objectMapper) {
        return new MessengerImpl(restTemplate, messengerProperties, objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(MessengerSender.class)
    public MessengerSender messageSender(RestTemplate restTemplate,
                                                       MessengerProperties messengerProperties,
                                                       PlatformSender platformSender,
                                                       @Qualifier("tokenizedSenderTaskExecutor") TaskExecutor taskExecutor,
                                                       InterceptorsTrigger interceptorsTrigger) {
        MessengerSender sender = new MessengerSender(
                restTemplate,
                taskExecutor,
                interceptorsTrigger,
                messengerProperties
        );

        platformSender.addSender(Platform.FB_MESSENGER, sender);
        return sender;
    }

    @Bean
    @ConditionalOnMissingBean(EventTypeResolver.class)
    public EventTypeResolver eventTypeResolver() {
        return new DefaultEventTypeResolver();
    }

    @Bean
    @ConditionalOnMissingBean(value = UserProvider.class)
    public UserProvider userProvider() {
        return new DefaultUserProvider();
    }

    @Bean
    @ConditionalOnMissingBean(value = BotProvider.class)
    public BotProvider botProvider(MessengerProperties messengerProperties) {
        return new DefaultBotProvider(messengerProperties.getAccessToken());
    }

    @Bean
    @ConditionalOnMissingBean(value = ExceptionHandler.class)
    public ExceptionHandler defaultExceptionHandler() {
        return new DefaultExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = InterceptorsTrigger.class)
    public InterceptorsTrigger defaultInterceptorsTrigger(
            @Autowired(required = false) List<MessengerInterceptor<GetEvent>> getEventInterceptors,
            @Autowired(required = false) List<MessengerInterceptor<ProcessedEvent>> processedEventInterceptors,
            @Autowired(required = false) List<MessengerInterceptor<BeforeSendMessage>> beforeSendInterceptors,
            @Autowired(required = false) List<MessengerInterceptor<AfterSendMessage>> afterSendInterceptors) {
        return new DefaultInterceptorsTrigger(
                getThisOrEmptyIfNull(getEventInterceptors),
                getThisOrEmptyIfNull(processedEventInterceptors),
                getThisOrEmptyIfNull(beforeSendInterceptors),
                getThisOrEmptyIfNull(afterSendInterceptors));
    }

    private List getThisOrEmptyIfNull(List list) {
        if (list != null) {
            return list;
        } else return new ArrayList();
    }

    @Bean
    @ConditionalOnMissingBean(value = ReportHandler.class)
    public ReportHandler reportHandler(EventTypeResolver eventTypeResolver,
                                       @Qualifier("defaultReportHandlerTaskExecutor") TaskExecutor taskExecutor,
                                       List<EventHandler> eventHandlers,
                                       BotProvider botProvider,
                                       UserProvider userProvider,
                                       InterceptorsTrigger interceptorsTrigger,
                                       @Autowired(required = false) ExceptionHandler exceptionHandler) {
        return new DefaultReportHandler(
                eventTypeResolver,
                taskExecutor,
                eventHandlers,
                botProvider,
                userProvider,
                interceptorsTrigger,
                exceptionHandler);
    }

    @Bean
    @ConditionalOnMissingBean(SubscriptionReviewer.class)
    public SubscriptionReviewer subscriptionReviewer(MessengerProperties properties) {
        return new DefaultSubscriptionReviewer(properties);
    }

    @Bean
    @ConditionalOnMissingBean(MessengerEventController.class)
    public MessengerEventController messengerEventController(ReportHandler reportHandler,
                                                             SubscriptionReviewer subscriptionReviewer) {
        return new MessengerEventController(reportHandler, subscriptionReviewer);
    }

    @Bean
    @ConditionalOnMissingBean(value = ObjectMapper.class)
    public ObjectMapper defaultJacksonObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean(value = RestTemplate.class)
    public RestTemplate defaultRestTemplate(ObjectMapper objectMapper) {
        List<HttpMessageConverter<?>> httpMessageConverters = Arrays.asList(
                new ByteArrayHttpMessageConverter(),
                new StringHttpMessageConverter(),
                new ResourceHttpMessageConverter(),
                new SourceHttpMessageConverter<>(),
                new AllEncompassingFormHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter(objectMapper));

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(httpMessageConverters);
        return restTemplate;
    }

    @Bean(name = "defaultReportHandlerTaskExecutor")
    public TaskExecutor eventHandlingTaskExecutor(HandlerTaskExecutorProperties properties) {
        return createExecutorWithProperties(properties);
    }

    @Bean(name = "tokenizedSenderTaskExecutor")
    public TaskExecutor messageSendingTaskExecutor(SenderTaskExecutorProperties properties) {
        return createExecutorWithProperties(properties);
    }

    private TaskExecutor createExecutorWithProperties(ExecutorProperties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.initialize();
        return executor;
    }
}
