package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public String error;

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

    public StrapResponse<String> call(String serviceName, String method, Map<String, String> params) {
        if (StrapSDK.resources.get(serviceName) == null) {
            return new StrapResponse(null, "Could not find resource.");
        }

        StrapSDK.resources.get(serviceName).setToken(StrapSDK.token);
        StrapSDK.resources.get(serviceName).setMethod(method);

        return StrapSDK.resources.get(serviceName).call(method, params);
    }

    public StrapResponse<ArrayList<StrapReport>> getActivity(Map<String, String> params) {
        StrapResponse<String> res = call("activity", "GET", params);
        ArrayList<StrapReport> rs = jsonToReportsList(res.data);
        StrapResponse<ArrayList<StrapReport>> rv = new StrapResponse<>(rs, res.error);
        return rv;
    }

    public StrapResponse<ArrayList<StrapReport>> getToday() {
        Map<String, String> params = new HashMap<>();
        return this.getToday(params);
    }

    public StrapResponse<ArrayList<StrapReport>> getToday(Map<String, String> params) {
        StrapResponse<String> res = call("today", "GET", params);
        ArrayList<StrapReport> rs = jsonToReportsList(res.data);
        StrapResponse<ArrayList<StrapReport>> rv = new StrapResponse<>(rs, res.error);
        return rv;
    }

    public StrapResponse<StrapReport> getReport(Map<String, String> params) {
        StrapResponse<String> res = call("report", "GET", params);
        Type reportType = new TypeToken<StrapReport>() {}.getType();
        StrapReport r = JSON.fromJson(res.data, reportType);
        StrapResponse<StrapReport> rv = new StrapResponse<>(r, res.error);
        return rv;

    }

    public StrapResponse<ArrayList<Map<String,String>>> getUsers() {
        Map<String, String> params = new HashMap<>();
       return this.getUsers(params);
    }

    public StrapResponse<ArrayList<Map<String,String>>> getUsers(Map<String, String> params) {
         StrapResponse<String> res = call("users", "GET", params);
         
        JsonParser parser = new JsonParser();

        JsonArray arr = parser.parse(res.data).getAsJsonArray();

        StrapResponse<ArrayList<Map<String,String>>> rv = new StrapResponse<>();
        ArrayList<Map<String,String>> us = new ArrayList<>();
        for (int i = 0, numObjs = arr.size(); i < numObjs; i++) {
            Type userType = new TypeToken<Map<String,String>>() {
            }.getType();
            Map<String,String> u = JSON.fromJson(arr.get(i), userType);
            us.add(u);
        }
        rv.data = us;
        return rv;
    }

    public StrapResponse getTrigger(Map<String, String> params) {
        return call("trigger", "GET", params);
    }

    private ArrayList<StrapReport> jsonToReportsList(String jsonStr) {
        JsonParser parser = new JsonParser();

        JsonArray arr = parser.parse(jsonStr).getAsJsonArray();

        ArrayList<StrapReport> rv = new ArrayList<>();
        for (int i = 0, numObjs = arr.size(); i < numObjs; i++) {
            Type reportType = new TypeToken<StrapReport>() {
            }.getType();
            StrapReport r = JSON.fromJson(arr.get(i), reportType);
            rv.add(r);
        }
        return rv;
    }
}
