package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

    public StrapSDKBase(String token) throws StrapResponseParseException {
        StrapSDKBase.token = token;
        StrapSDKBase.resources = discover();
        this.fillResourcesDefaults();
    }

    private Map<String, Resource> discover() throws StrapResponseParseException {
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
            throw new StrapResponseParseException("Failed to parse response from discovery endpoint: Check your token.");
        }
        return resMap;
    }

    public static Resource getResourceByName(String name) {
        return StrapSDKBase.resources.get(name);
    }

    private void fillResourcesDefaults() {
        for (Map.Entry service : StrapSDKBase.resources.entrySet()) {
            // fill name
            String name = (String) service.getKey();
            StrapSDKBase.getResourceByName(name).setName(name);
            
            // fill token
            StrapSDKBase.resources.get(name).setToken(StrapSDKBase.token);
            
            // fill method
            StrapSDKBase.getResourceByName(name).setMethod("GET");
            
            // fill required if null
            if (StrapSDKBase.getResourceByName(name).getRequired() == null) {
                StrapSDKBase.getResourceByName(name).setRequired(new ArrayList<String>());
            }

            // fill optional if null
            if (StrapSDKBase.getResourceByName(name).getOptional() == null) {
                StrapSDKBase.getResourceByName(name).setOptional(new ArrayList<String>());
            }

        }
    }

    protected PagedResponse call(String serviceName, String method, Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        if (StrapSDKBase.getResourceByName(serviceName) == null) {
            throw new StrapResourceNotFoundException("Could not find resource: " + serviceName);
        }

        return StrapSDKBase.getResourceByName(serviceName).call(method, params);
    }
}
