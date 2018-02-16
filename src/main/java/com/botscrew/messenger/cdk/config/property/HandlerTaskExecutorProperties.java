package com.botscrew.messenger.cdk.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "facebook.messenger.executor")
@Getter
@Setter
public class HandlerTaskExecutorProperties {

    private Integer corePoolSize = 5;
    private Integer maxPoolSize = 25;
    private Integer queueCapacity = 500;
    private Integer keepAliveSeconds = 10;
}
