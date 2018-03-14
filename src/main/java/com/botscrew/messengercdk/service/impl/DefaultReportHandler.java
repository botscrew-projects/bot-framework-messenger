package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.model.incomming.EventType;
import com.botscrew.messengercdk.model.incomming.Messaging;
import com.botscrew.messengercdk.model.incomming.MessagingBundle;
import com.botscrew.messengercdk.model.incomming.Report;
import com.botscrew.messengercdk.service.EventProcessor;
import com.botscrew.messengercdk.service.EventTypeResolver;
import com.botscrew.messengercdk.service.ReportHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
