package com.botscrew.messengercdk.service.impl;

import com.botscrew.messengercdk.service.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExceptionHandler implements ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public boolean handle(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return true;
    }
}
