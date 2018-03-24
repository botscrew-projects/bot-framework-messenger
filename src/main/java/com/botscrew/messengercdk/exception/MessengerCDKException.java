package com.botscrew.messengercdk.exception;

public class MessengerCDKException extends RuntimeException {

    public MessengerCDKException(String message) {
        super(message);
    }

    public MessengerCDKException(String message, Throwable cause) {
        super(message, cause);
    }
}
