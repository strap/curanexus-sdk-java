package com.strap.sdk.java;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapToday {

    protected final ResourceManager serviceManager;

    public StrapToday(String token) {
        this.serviceManager = new ResourceManager(token);
    }

    public StrapToday(ResourceManager manager) {
        this.serviceManager = manager;
    }

    public StrapResponse get() {
        Map<String, String> params = new HashMap<>();
        return this.serviceManager.call("today", "GET", params);
    }

    public StrapResponse get(Map<String, String> params) {
        return this.serviceManager.call("today", "GET", params);
    }

    public StrapResponse put(Map<String, String> params) {
        return this.serviceManager.call("today", "PUT", params);

    }

    public StrapResponse post(Map<String, String> params) {
        return this.serviceManager.call("today", "POST", params);

    }

    public StrapResponse delete(Map<String, String> params) {
        return this.serviceManager.call("today", "DELETE", params);

    }
}
