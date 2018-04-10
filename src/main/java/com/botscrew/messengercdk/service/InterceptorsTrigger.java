package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.domain.action.AfterSendMessage;
import com.botscrew.messengercdk.domain.action.BeforeSendMessage;
import com.botscrew.messengercdk.domain.action.GetEvent;
import com.botscrew.messengercdk.domain.action.ProcessedEvent;

public interface InterceptorsTrigger {

    void trigger(GetEvent getEvent);

    void trigger(ProcessedEvent processedEvent);

    void trigger(BeforeSendMessage beforeSendMessage);

    void trigger(AfterSendMessage afterSendMessage);

}
