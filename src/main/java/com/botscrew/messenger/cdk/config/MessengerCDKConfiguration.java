package com.botscrew.messenger.cdk.config;

import com.botscrew.framework.flow.container.LocationContainer;
import com.botscrew.framework.flow.container.PostbackContainer;
import com.botscrew.framework.flow.container.TextContainer;
import com.botscrew.messenger.cdk.config.property.HandlerTaskExecutorProperties;
import com.botscrew.messenger.cdk.config.property.MessengerProperties;
import com.botscrew.messenger.cdk.config.property.SenderExecutorProperties;
import com.botscrew.messenger.cdk.controller.MessengerEventController;
import com.botscrew.messenger.cdk.service.*;
import com.botscrew.messenger.cdk.service.impl.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(value = {
        MessengerProperties.class,
        HandlerTaskExecutorProperties.class,
        SenderExecutorProperties.class
})
public class MessengerCDKConfiguration {

    @Bean
    public TokenizedSender messageSender(RestTemplate restTemplate,
                                         MessengerProperties messengerProperties,
                                         @Qualifier("defaultSenderTaskScheduler") ThreadPoolTaskScheduler scheduler) {
        return new TokenizedSenderImpl(restTemplate, messengerProperties, scheduler);
    }

    @Bean
    public Sender sender(TokenizedSender tokenizedSender, MessengerProperties messengerProperties) {
        return new DefaultSender(tokenizedSender, messengerProperties);
    }

    @Bean
    public EventTypeResolver eventTypeResolver() {
        return new DefaultEventTypeResolver();
    }

    @Bean
    @ConditionalOnMissingBean(value = UserProvider.class)
    public UserProvider userProvider() {
        return new DefaultUserProvider();
    }

    @Bean
    @ConditionalOnMissingBean(value = EventProcessor.class)
    public EventProcessor eventProcessor(UserProvider userProvider,
                                         TextContainer textContainer,
                                         PostbackContainer postbackContainer,
                                         LocationContainer locationContainer) {
        return new BotFrameworkEventProcessor(userProvider, textContainer, postbackContainer, locationContainer);
    }

    @Bean
    @ConditionalOnMissingBean(value = ReportHandler.class)
    public ReportHandler reportHandler(EventTypeResolver eventTypeResolver,
                                       EventProcessor eventProcessor,
                                       @Qualifier("defaultReportHandlerTaskExecutor") TaskExecutor taskExecutor) {
        return new DefaultReportHandler(eventTypeResolver, eventProcessor, taskExecutor);
    }

    @Bean
    public SubscriptionReviewer subscriptionReviewer(MessengerProperties properties) {
        return new DefaultSubscriptionReviewer(properties);
    }

    @Bean
    public MessengerEventController messengerEventController(ReportHandler reportHandler, SubscriptionReviewer subscriptionReviewer) {
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
    public TaskExecutor taskExecutor(HandlerTaskExecutorProperties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.initialize();

        return executor;
    }

    @Bean(name = "defaultSenderTaskScheduler")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(SenderExecutorProperties properties) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(properties.getPoolSize());
        scheduler.initialize();

        return scheduler;
    }
}
