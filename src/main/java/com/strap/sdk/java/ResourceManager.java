package com.strap.sdk.java;

// Google lib for working with JSON [de]serialization
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public final class ResourceManager {

    private static final Gson JSON = new Gson();
    private static final String discoveryURL = "https://api2.straphq.com/discover";
    private static String token;
    private static Map<String, Resource> resources = new HashMap<>();

    public ResourceManager(String token) {
        ResourceManager.token = token;
        ResourceManager.resources = discover();
    }

    private Map<String, Resource> discover() {
        String res = HttpRequest
                .get(discoveryURL)
                .header("X-Auth-Token", ResourceManager.token)
                .body();

//        save response to resources map using JSON
        Type resourceMapType = new TypeToken< Map<String, Resource>>() {
        }.getType();
        return JSON.fromJson(res, resourceMapType);
    }

    public StrapResponse call(String serviceName, String method, Map<String, String> params) {
        if (ResourceManager.resources.get(serviceName) == null) {
            return new StrapResponse(null, "Could not find resource.");
        }

        ResourceManager.resources.get(serviceName).setToken(ResourceManager.token);
        ResourceManager.resources.get(serviceName).setMethod(method);

        return ResourceManager.resources.get(serviceName).call(method, params);
    }

    private Map<String, String> mapFromJSON(String body) {
        Type resourceMapType = new TypeToken< Map<String, String>>() {
        }.getType();
        Map<String, String> res = JSON.fromJson(body, resourceMapType);
        return res;
    }
}
