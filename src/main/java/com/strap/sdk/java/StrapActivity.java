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

    public StrapResponse put(Map<String, String> params) {
        return this.serviceManager.call("activity", "PUT", params);

    }

    public StrapResponse post(Map<String, String> params) {
        return this.serviceManager.call("activity", "POST", params);

    }

    public StrapResponse delete(Map<String, String> params) {
        return this.serviceManager.call("activity", "DELETE", params);

    }
}
