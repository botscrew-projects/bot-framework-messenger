package com.botscrew.messengercdk.config.property;

public interface ExecutorProperties {

    Integer getCorePoolSize();

    Integer getMaxPoolSize();

    Integer getQueueCapacity();

    Integer getKeepAliveSeconds();
}
