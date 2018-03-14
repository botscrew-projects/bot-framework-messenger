package com.botscrew.messengercdk.service;

import com.botscrew.messengercdk.model.incomming.Report;

public interface ReportHandler {
    void handle(Report report);
}
