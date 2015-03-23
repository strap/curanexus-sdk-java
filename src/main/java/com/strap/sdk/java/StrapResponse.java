package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 * @param <T>
 */
public class StrapResponse<T> extends StrapPagedResponse {

    public T data;
    public String error;

    public StrapResponse() {
        this.data = null;
        this.error = "";
    }

    public StrapResponse(T data, String error) {
        this.data = data;
        this.error = error;
    }
}
