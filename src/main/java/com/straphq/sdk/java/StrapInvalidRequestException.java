package com.straphq.sdk.java;

public class StrapInvalidRequestException extends Exception {

    public StrapInvalidRequestException() {
        super();
    }

    public StrapInvalidRequestException(String message) {
        super(message);
    }

    public StrapInvalidRequestException(Throwable throwable) {
        super(throwable);
    }

    public StrapInvalidRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
