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
import com.botscrew.botframework.sender.PlatformSender;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockContainersConfiguration {
    @Bean
    public LocationContainer locationContainer() {
        return Mockito.mock(LocationContainer.class);
    }

    @Bean
    public PostbackContainer postbackContainer() {
        return Mockito.mock(PostbackContainer.class);
    }

    @Bean
    public TextContainer textContainer() {
        return Mockito.mock(TextContainer.class);
    }

    @Bean
    public ReferralContainer referralContainer() {
        return Mockito.mock(ReferralContainer.class);
    }

    @Bean
    public ReadContainer readContainer() {
        return Mockito.mock(ReadContainer.class);
    }

    @Bean
    public EchoContainer echoContainer() {
        return Mockito.mock(EchoContainer.class);
    }

    @Bean
    public DeliveryContainer deliveryContainer() {
        return Mockito.mock(DeliveryContainer.class);
    }

    @Bean
    public PlatformSender platformSender() {
        return Mockito.mock(PlatformSender.class);
    }
}
