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
        this.discover();
    }

    public void discover() {
//        TODO: exception handling for http response
        String services = HttpRequest
                .get(discoveryURL)
                .header("X-Auth-Token", ResourceManager.token)
                .body();

//        save response to resources map using JSON
        Type resourceMapType = new TypeToken< Map<String, Resource>>() {
        }.getType();
        ResourceManager.resources = JSON.fromJson(services, resourceMapType);
    }

    public StrapResponse call(String serviceName, String method, Map<String, String> params) {
        if (ResourceManager.resources.get(serviceName) == null) {
            return new StrapResponse(null, "Could not find resource.");
        }

        ResourceManager.resources.get(serviceName).setToken(ResourceManager.token);
        ResourceManager.resources.get(serviceName).setMethod(method);

        return ResourceManager.resources.get(serviceName).call(method, params);
    }
}
