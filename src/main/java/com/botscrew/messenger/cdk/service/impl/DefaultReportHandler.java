package com.botscrew.messenger.cdk.service.impl;

import com.botscrew.messenger.cdk.model.incomming.EventType;
import com.botscrew.messenger.cdk.model.incomming.Messaging;
import com.botscrew.messenger.cdk.model.incomming.MessagingBundle;
import com.botscrew.messenger.cdk.model.incomming.Report;
import com.botscrew.messenger.cdk.service.EventProcessor;
import com.botscrew.messenger.cdk.service.EventTypeResolver;
import com.botscrew.messenger.cdk.service.ReportHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

@Slf4j
@RequiredArgsConstructor
public class DefaultReportHandler implements ReportHandler {
    private final EventTypeResolver typeResolver;
    private final EventProcessor eventProcessor;
    private final TaskExecutor taskExecutor;

    @Override
    public void handle(Report report) {
        taskExecutor.execute(() -> {
            log.debug("Messenger report: {}", report);
            for (MessagingBundle bundle : report.getEntry()) {
                handleMessagingBundle(bundle);
            }
        });
    }

    private void handleMessagingBundle(MessagingBundle bundle) {
        for (Messaging messaging : bundle.getMessaging()) {
            EventType type = typeResolver.resolve(messaging);
            eventProcessor.process(type, messaging);
        }
    }
}
