package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapResponseParseException extends Exception {
    public StrapResponseParseException(){
        super();
    }
    
    public StrapResponseParseException(String msg){
        super(msg);
    }

    /**
     *
     * @param message
     * @param throwable
     */
    public StrapResponseParseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
