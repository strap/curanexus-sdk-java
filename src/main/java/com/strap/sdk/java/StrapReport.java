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
public class StrapReport {

    protected final ResourceManager serviceManager;

    public StrapReport(String token) {
        this.serviceManager = new ResourceManager(token);
    }

    public StrapReport(ResourceManager manager) {
        this.serviceManager = manager;
    }

    public StrapResponse get(Map<String, String> params) {
        return this.serviceManager.call("report", "GET", params);
    }
}
