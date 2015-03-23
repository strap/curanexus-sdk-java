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
public class StrapSDKBase {
    
    protected final Gson JSON = new Gson();
    private static final String discoveryURL = "https://api2.straphq.com/discover";
    private static String token;
    private static Map<String, Resource> resources = new HashMap<>();
    public String error;

    public StrapSDKBase(String token) {
        StrapSDKBase.token = token;
        StrapSDKBase.resources = discover();
    }

    private Map<String, Resource> discover() {
        String res = HttpRequest
                .get(discoveryURL)
                .header("X-Auth-Token", StrapSDKBase.token)
                .body();

//        save response to resources map using JSON
        Map<String, Resource> resMap;

        Type resMapType = new TypeToken< Map<String, Resource>>() {
        }.getType();
        try {
            resMap = JSON.fromJson(res, resMapType);
        } catch (Exception e) {
            resMap = null;
            this.error = "Failed to parse response from discovery endpoint: Check your token.";
        }
        return resMap;
    }

    protected StrapResponse<String> call(String serviceName, String method, Map<String, String> params) {
        if (StrapSDKBase.resources.get(serviceName) == null) {
            return new StrapResponse(null, "Could not find resource.");
        }

        StrapSDKBase.resources.get(serviceName).setToken(StrapSDKBase.token);
        StrapSDKBase.resources.get(serviceName).setMethod(method);

        return StrapSDKBase.resources.get(serviceName).call(method, params);
    }
    
    public boolean hasError(){
        return (this.error != null);
    }
}
