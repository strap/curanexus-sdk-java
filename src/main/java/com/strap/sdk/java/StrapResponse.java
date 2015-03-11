package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapResponse {

    public StrapModel data;
    public String error;

    public StrapResponse() {
        this.data = null;
        this.error = "";
    }

    public StrapResponse(StrapModel data, String error) {
        this.data = data;
        this.error = error;
    }
}
