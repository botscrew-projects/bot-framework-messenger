package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.domain.MessengerInterceptor;
import com.botscrew.messengercdk.domain.action.AfterSendMessage;
import com.botscrew.messengercdk.domain.action.BeforeSendMessage;
import com.botscrew.messengercdk.domain.action.GetEvent;
import com.botscrew.messengercdk.domain.action.ProcessedEvent;
import com.botscrew.messengercdk.service.InterceptorsTrigger;

import java.util.List;

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
            interceptor.onAction(getEvent);
        }
    }

    @Override
    public void trigger(ProcessedEvent processedEvent) {
        for (MessengerInterceptor<ProcessedEvent> interceptor : processedEventInterceptors) {
            interceptor.onAction(processedEvent);
        }
    }

    @Override
    public void trigger(BeforeSendMessage beforeSendMessage) {
        for (MessengerInterceptor<BeforeSendMessage> interceptor : beforeSendMessageInterceptors) {
            interceptor.onAction(beforeSendMessage);
        }
    }

    @Override
    public void trigger(AfterSendMessage afterSendMessage) {
        for (MessengerInterceptor<AfterSendMessage> interceptor : afterSendMessageInterceptors) {
            interceptor.onAction(afterSendMessage);
        }
    }
}
