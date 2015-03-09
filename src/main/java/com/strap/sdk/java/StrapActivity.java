/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public String get(Map<String, String> params) throws Exception {
        return this.serviceManager.call("activity", "GET", params);
    }

    public String put(Map<String, String> params) throws Exception {
        return this.serviceManager.call("activity", "PUT", params);

    }

    public String post(Map<String, String> params) throws Exception {
        return this.serviceManager.call("activity", "POST", params);

    }

    public String delete(Map<String, String> params) throws Exception {
        return this.serviceManager.call("activity", "DELETE", params);

    }
}
