package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.model.incomming.Report;
import com.botscrew.messengercdk.service.ExceptionHandler;
import com.botscrew.messengercdk.service.ReportHandler;

public class ErrorHandlingReportHandlerWrapper implements ReportHandler {
    private final ReportHandler originalReportHandler;
    private final ExceptionHandler exceptionHandler;


    public ErrorHandlingReportHandlerWrapper(ReportHandler originalReportHandler,
                                             ExceptionHandler exceptionHandler) {
        this.originalReportHandler = originalReportHandler;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void handle(Report report) {
        try {
            originalReportHandler.handle(report);
        } catch (Exception e) {
            boolean handled = exceptionHandler.handle(e);
            if (!handled) throw e;
        }
    }
}
