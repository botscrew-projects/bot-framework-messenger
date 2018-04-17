package com.botscrew.messengercdk.exception;

public class GraphAPIException extends MessengerCDKException {
    public GraphAPIException(String message) {
        super(message);
    }

    public GraphAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
