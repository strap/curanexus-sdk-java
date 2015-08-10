package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
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
    private static final HashMap<String, Resource[]> resources = new HashMap<>();

    public StrapSDKBase(String token) throws StrapResponseParseException {
        StrapSDKBase.token = token;
        HashMap<String,Resource[]> discover = discover();
        StrapSDKBase.resources.putAll(this.fillResourcesDefaults( discover ));
    }

    private HashMap<String, Resource[]> discover() throws StrapResponseParseException {
        String res = HttpRequest
                .get(discoveryURL)
                .header("X-Auth-Token", StrapSDKBase.token)
                .body();

        // save response to resources map using JSON
        HashMap<String, Resource[]> resMap;

        Type resMapType = new TypeToken< HashMap<String, Resource[]>>() { }.getType();
        try {
            resMap = JSON.fromJson(res, resMapType);
        } catch (Exception e) {
            throw new StrapResponseParseException("Failed to parse response from discovery endpoint: Check your token.");
        }
        return resMap;
    }

    public static Resource[] getResourceByName(String name) {
        return StrapSDKBase.resources.get(name);
    }

    private HashMap<String,Resource[]> fillResourcesDefaults(HashMap<String,Resource[]> m) {
        HashMap<String, Resource[]> discover = new HashMap<>();
        discover.putAll(m);
        
        for (Map.Entry r : discover.entrySet()) {
            String name = (String) r.getKey();
            Resource[] endpoints = discover.get(name);


            for(Resource endpoint : endpoints) {
                endpoint.setName(name);
                endpoint.setToken(StrapSDKBase.token);
            }

        }

        return discover;
    }



    protected Object call(String serviceName, String method, Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        return call(serviceName, method, params, null);
    }

    protected Object call(String serviceName, String method, Map<String, String> params, Object body) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        if (StrapSDKBase.getResourceByName(serviceName) == null) {
            throw new StrapResourceNotFoundException("Could not find resource: " + serviceName);
        }

        Resource[] resources = StrapSDKBase.getResourceByName(serviceName);
        for(Resource r : resources) {
            if(r.method.equals(method)) {

                return r.call(method, params, body);

            }
        }

        throw new StrapResourceNotFoundException("'" + serviceName + "' resource does not support method " + method);
    }
}
