package com.strap.sdk.java;

import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapActivity {

    protected final ResourceManager serviceManager;

    public StrapActivity(String token) {
        this.serviceManager = new ResourceManager(token);
    }

    public StrapActivity(ResourceManager manager) {
        this.serviceManager = manager;
    }

    public StrapResponse get(Map<String, String> params) {
        return this.serviceManager.call("activity", "GET", params);
    }

}
