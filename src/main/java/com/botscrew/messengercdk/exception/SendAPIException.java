package com.botscrew.messengercdk.exception;

public class SendAPIException extends MessengerCDKException {

    public SendAPIException(String message) {
        super(message);
    }

    public SendAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
