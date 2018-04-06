package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.domain.action.*;

public interface InterceptorsTrigger {

    void trigger(GetEvent getEvent);
    void trigger(ProcessedEvent processedEvent);
    void trigger(BeforeSendMessage beforeSendMessage);
    void trigger(AfterSendMessage afterSendMessage);

}
