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

package com.botscrew.messengercdk.config.property;

import com.botscrew.messengercdk.config.property.ExecutorProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Implementation of {@link ExecutorProperties} for describing event handler executor
 * Contains default values for the properties.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "facebook.messenger.events-executor")
public class HandlerTaskExecutorProperties implements ExecutorProperties {
    private Integer corePoolSize = 5;
    private Integer maxPoolSize = 25;
    private Integer queueCapacity = 500;
    private Integer keepAliveSeconds = 10;
}
