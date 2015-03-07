/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.strap.sdk.java;



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

    public String get(Map<String, String> params) throws Exception {
        return this.serviceManager.call("today", "GET", params);
    }

    public String put(Map<String, String> params) throws Exception {
        return this.serviceManager.call("today", "PUT", params);

    }

    public String post(Map<String, String> params) throws Exception {
        return this.serviceManager.call("today", "POST", params);

    }

    public String delete(Map<String, String> params) throws Exception {
        return this.serviceManager.call("today", "DELETE", params);

    }
}
