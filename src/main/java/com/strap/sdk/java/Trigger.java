package com.strap.sdk.java;

import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapTrigger extends StrapPagedResponse {

    public StrapTriggerModel data;
    public String error;
    private StrapSDK strap;
    private String service;
    Map<String, String> params;

    public StrapTrigger(StrapSDK strap, String service, Map<String, String> params, StrapTriggerModel data, String error) {
        this.strap = strap;
        this.service = service;
        this.params = params;
        this.data = data;
        this.error = error;
    }

    public StrapTrigger(StrapTriggerModel data) {
        this.data = data;
        this.error = "";
    }

    public StrapTrigger(StrapTriggerModel data, String error) {
        this.data = data;
        this.error = error;
    }
}
