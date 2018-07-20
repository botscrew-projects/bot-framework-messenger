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

package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.domain.Interruption;
import com.botscrew.messengercdk.domain.MessengerInterceptor;
import com.botscrew.messengercdk.domain.action.AfterSendMessage;
import com.botscrew.messengercdk.domain.action.BeforeSendMessage;
import com.botscrew.messengercdk.domain.action.GetEvent;
import com.botscrew.messengercdk.domain.action.ProcessedEvent;
import com.botscrew.messengercdk.exception.InterceptorInterruptedException;
import com.botscrew.messengercdk.service.InterceptorsTrigger;

import java.util.List;

/**
 * Implementation of {@link InterceptorsTrigger} used by default
 */
public class DefaultInterceptorsTrigger implements InterceptorsTrigger {

    private final List<MessengerInterceptor<GetEvent>> getEventInterceptors;
    private final List<MessengerInterceptor<ProcessedEvent>> processedEventInterceptors;
    private final List<MessengerInterceptor<BeforeSendMessage>> beforeSendMessageInterceptors;
    private final List<MessengerInterceptor<AfterSendMessage>> afterSendMessageInterceptors;

    public DefaultInterceptorsTrigger(List<MessengerInterceptor<GetEvent>> getEventInterceptors,
                                      List<MessengerInterceptor<ProcessedEvent>> processedEventInterceptors,
                                      List<MessengerInterceptor<BeforeSendMessage>> beforeSendMessageInterceptors,
                                      List<MessengerInterceptor<AfterSendMessage>> afterSendMessageInterceptors) {
        this.getEventInterceptors = getEventInterceptors;
        this.processedEventInterceptors = processedEventInterceptors;
        this.beforeSendMessageInterceptors = beforeSendMessageInterceptors;
        this.afterSendMessageInterceptors = afterSendMessageInterceptors;
    }

    @Override
    public void trigger(GetEvent getEvent) {
        for (MessengerInterceptor<GetEvent> interceptor : getEventInterceptors) {
            Interruption interruption = interceptor.onAction(getEvent);
            check(interruption);
        }
    }

    @Override
    public void trigger(ProcessedEvent processedEvent) {
        for (MessengerInterceptor<ProcessedEvent> interceptor : processedEventInterceptors) {
            Interruption interruption = interceptor.onAction(processedEvent);
            check(interruption);
        }
    }

    @Override
    public void trigger(BeforeSendMessage beforeSendMessage) {
        for (MessengerInterceptor<BeforeSendMessage> interceptor : beforeSendMessageInterceptors) {
            Interruption interruption = interceptor.onAction(beforeSendMessage);
            check(interruption);
        }
    }

    @Override
    public void trigger(AfterSendMessage afterSendMessage) {
        for (MessengerInterceptor<AfterSendMessage> interceptor : afterSendMessageInterceptors) {
            Interruption interruption = interceptor.onAction(afterSendMessage);
            check(interruption);
        }
    }

    private void check(Interruption interruption) {
        if (interruption.isInterrupt()) {
            String message = "Interceptor interrupted execution with reason: " + interruption.getCause();
            throw new InterceptorInterruptedException(message);
        }
    }
}
