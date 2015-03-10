package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapResponse {

    public String body;
    public String error;

    public StrapResponse() {
        this.body = "";
        this.error = "";
    }

    public StrapResponse(String body, String error) {
        this.body = body;
        this.error = error;
    }
}
