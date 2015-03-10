package com.strap.sdk.java;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapUsers {

    protected final ResourceManager serviceManager;

    public StrapUsers(String token) {
        this.serviceManager = new ResourceManager(token);
    }

    public StrapUsers(ResourceManager manager) {
        this.serviceManager = manager;
    }

    public StrapResponse get() {
        Map<String, String> params = new HashMap<>();
        return this.serviceManager.call("users", "GET", params);
    }

    public StrapResponse get(Map<String, String> params) {
        return this.serviceManager.call("users", "GET", params);
    }

    public StrapResponse put(Map<String, String> params) {
        return this.serviceManager.call("users", "PUT", params);

    }

    public StrapResponse post(Map<String, String> params) {
        return this.serviceManager.call("users", "POST", params);

    }

    public StrapResponse delete(Map<String, String> params) {
        return this.serviceManager.call("users", "DELETE", params);

    }
}
