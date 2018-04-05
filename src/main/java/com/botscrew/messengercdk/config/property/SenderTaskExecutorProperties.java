package com.botscrew.messengercdk.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "facebook.messenger.sender-executor")
public class SenderTaskExecutorProperties implements ExecutorProperties {
    private Integer corePoolSize = 5;
    private Integer maxPoolSize = 25;
    private Integer queueCapacity = 500;
    private Integer keepAliveSeconds = 10;
}
