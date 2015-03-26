package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapMalformedUrlException extends Exception {
    public StrapMalformedUrlException(){
        super();
    }
    
    public StrapMalformedUrlException(String msg){
        super(msg);
    }

    /**
     *
     * @param message
     * @param throwable
     */
    public StrapMalformedUrlException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
