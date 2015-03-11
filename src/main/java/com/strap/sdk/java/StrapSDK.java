package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapSDK {

    private static final Gson JSON = new Gson();
    private static final String discoveryURL = "https://api2.straphq.com/discover";
    private static String token;
    private static Map<String, Resource> resources = new HashMap<>();

    public StrapSDK(String token) {
        StrapSDK.token = token;
        StrapSDK.resources = discover();
    }

    private Map<String, Resource> discover() {
        String res = HttpRequest
                .get(discoveryURL)
                .header("X-Auth-Token", StrapSDK.token)
                .body();

//        save response to resources map using JSON
        Type resourceMapType = new TypeToken< Map<String, Resource>>() {
        }.getType();
        return JSON.fromJson(res, resourceMapType);
    }

    private StrapResponse call(String serviceName, String method, Map<String, String> params) {
        if (StrapSDK.resources.get(serviceName) == null) {
            return new StrapResponse(null, "Could not find resource.");
        }

        StrapSDK.resources.get(serviceName).setToken(StrapSDK.token);
        StrapSDK.resources.get(serviceName).setMethod(method);

        return StrapSDK.resources.get(serviceName).call(method, params);
    }
 

    public StrapResponse getActivity(Map<String, String> params) {
        return call("activity", "GET", params);
    }

    public StrapResponse getReport(Map<String, String> params) {
        return call("report", "GET", params);

    }

    public StrapResponse getTrigger(Map<String, String> params) {
        return call("trigger", "GET", params);
    }

    public StrapResponse getToday(Map<String, String> params) {
        return call("today", "GET", params);
    }

    public StrapResponse getToday() {
        Map<String, String> params = new HashMap<>();
        return call("today", "GET", params);
    }

    public StrapResponse getUsers(Map<String, String> params) {
        return call("users", "GET", params);
    }

    public StrapResponse getUsers() {
        Map<String, String> params = new HashMap<>();
        return call("users", "GET", params);
    }
    
}
