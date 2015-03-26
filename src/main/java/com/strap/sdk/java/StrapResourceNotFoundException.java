package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapResourceNotFoundException extends Exception {
    public StrapResourceNotFoundException(){
        super();
    }
    
    public StrapResourceNotFoundException(String msg){
        super(msg);
    }

    /**
     *
     * @param message
     * @param throwable
     */
    public StrapResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
