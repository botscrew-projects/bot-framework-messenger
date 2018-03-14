package com.botscrew.messenger.cdk.service;

import com.botscrew.messenger.cdk.model.incomming.Report;

public interface ReportHandler {
    void handle(Report report);
}
