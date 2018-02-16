package com.botscrew.messenger.cdk.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "facebook.messenger.sender.executor")
@Getter
@Setter
public class SenderExecutorProperties {

    private Integer poolSize = 10;
}
