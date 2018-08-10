package com.straphq.sdk.java;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.straphq.sdk.java.models.StrapModel;
import com.straphq.sdk.java.models.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class StrapSDK {

    private static final String API_DISCOVERY = "https://api.curanexus.io/discover";
    private static final String API_AUTH_HEADER = "x-auth-token";
    private final String TOKEN;
    private CloseableHttpClient client;
    private Gson gson;
    private StrapRequest request;

    protected static Map<String, Map<String, Resource>> resources;


    public StrapSDK(String token) {

        TOKEN = token;
        gson = new Gson();
        client = HttpClients.createDefault();
        resources = new HashMap<>();
        request = new StrapRequest(this, client, TOKEN);

        discover();
    }


    private void discover() {

        try {

            HttpGet httpGet = new HttpGet(API_DISCOVERY);
            httpGet.setHeader(API_AUTH_HEADER, TOKEN);

            CloseableHttpResponse res = client.execute(httpGet);
            String resData;

            resData = EntityUtils.toString(res.getEntity());
            res.close();

            JsonObject json = gson.fromJson(resData, JsonObject.class);

            Set<Map.Entry<String,JsonElement>> entrySet = json.entrySet();

            for(Map.Entry<String, JsonElement> item : entrySet) {

                String key = item.getKey();
                JsonArray methods = item.getValue().getAsJsonArray();

                for(JsonElement elem : methods) {

                    Resource r = new Resource(key, elem.getAsJsonObject());
                    String method = r.getMethod();


                    if(!resources.containsKey(method))
                        resources.put(method, new HashMap<String, Resource>());

                    resources.get(method).put(key, r);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected StrapResponse call(Resource resource, Map<String, Object> params) throws IOException {
        return request.call(resource, params, null);
    }

    public StrapResponse get(String endpoint) throws IOException, StrapInvalidRequestException {
        return get(endpoint, null);
    }

    public StrapResponse get(String endpoint, Map<String, Object> params) throws StrapInvalidRequestException, IOException {
        return request.call(getResource("GET", endpoint), params, null);
    }

    public StrapResponse post(String endpoint, Map<String, Object> params, JsonElement data) throws StrapInvalidRequestException, IOException {
        return request.call(getResource("POST", endpoint), params, data);
    }

    public StrapResponse post(String endpoint, Map<String, Object> params, StrapModel data) throws StrapInvalidRequestException, IOException {
        return request.call(getResource("POST", endpoint), params, data);
    }

    public StrapResponse put(String endpoint, Map<String, Object> params, Object data) throws StrapInvalidRequestException, IOException {
        return request.call(getResource("PUT", endpoint), params, data);
    }

    public StrapResponse delete(String endpoint, Map<String, Object> params) throws StrapInvalidRequestException, IOException {
        return request.call(getResource("DELETE", endpoint), params, null);
    }


    private Resource getResource(String method, String name) throws StrapInvalidRequestException {

        if(resources.containsKey(method)) {
            Map<String, Resource> res = resources.get(method);

            if(res.containsKey(name)) {
                return res.get(name);
            }
        }

        throw new StrapInvalidRequestException(name + " does not support method " + method);

    }



}