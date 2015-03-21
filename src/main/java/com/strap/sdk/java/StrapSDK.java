package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
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

    public StrapReportList getActivity(Map<String, String> params) {
        StrapResponse<String> res = call("activity", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(rs, res.error);
        return rv;
    }

    public StrapReportList getToday() {
        Map<String, String> params = new HashMap<>();
        return this.getToday(params);
    }

    public StrapReportList getToday(Map<String, String> params) {
        StrapResponse<String> res = call("today", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(rs, res.error);
        return rv;
    }

    public StrapReportList getCurrentWeek() {
        Map<String, String> params = new HashMap<>();
        return this.getCurrentWeek(params);
    }

    public StrapReportList getCurrentWeek(Map<String, String> params) {
        StrapResponse<String> res = call("week", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(rs, res.error);
        return rv;
    }

    public StrapReportList getCurrentMonth() {
        Map<String, String> params = new HashMap<>();
        return this.getCurrentMonth(params);
    }

    public StrapReportList getCurrentMonth(Map<String, String> params) {
        StrapResponse<String> res = call("month", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(rs, res.error);
        return rv;
    }

    public StrapReport getReport(Map<String, String> params) {
        StrapResponse<String> res = call("report", "GET", params);
        Type reportType = new TypeToken<StrapReportModel>() {
        }.getType();
        StrapReport rv;
        try {
            StrapReportModel r = JSON.fromJson(res.data, reportType);
            rv = new StrapReport(r, res.error);
        } catch (JsonParseException e) {
            rv = new StrapReport(null, res.data);
        }
        return rv;

    }

    public StrapUserList getUsers() {
        Map<String, String> params = new HashMap<>();
        return this.getUsers(params);
    }

    public StrapUserList getUsers(Map<String, String> params) {
        StrapResponse<String> res = call("users", "GET", params);

        JsonParser parser = new JsonParser();

        JsonArray arr = parser.parse(res.data).getAsJsonArray();

        ArrayList<Map<String, String>> us = new ArrayList<>();
        for (int i = 0, numObjs = arr.size(); i < numObjs; i++) {
            Type userType = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> u = JSON.fromJson(arr.get(i), userType);
            us.add(u);
        }
        StrapUserList rv = new StrapUserList(us, res.error);
        return rv;
    }

    public StrapTrigger getTrigger(Map<String, String> params) {
        StrapResponse<String> res = call("trigger", "GET", params);
        Type trigType = new TypeToken<StrapTriggerModel>() {
        }.getType();
        StrapTrigger rv;
        try {
            String r = JSON.fromJson(res.data, trigType);
            rv = new StrapTrigger(r, res.error);
        } catch (JsonParseException e) {
            rv = new StrapTrigger(null, res.data);
        }
        return rv;
    }

    private ArrayList<StrapReportModel> jsonToReportList(String jsonStr) {
        JsonParser parser = new JsonParser();

        JsonArray arr = parser.parse(jsonStr).getAsJsonArray();

        ArrayList<StrapReportModel> rv = new ArrayList<>();
        for (int i = 0, numObjs = arr.size(); i < numObjs; i++) {
            Type reportType = new TypeToken<StrapReportModel>() {
            }.getType();
            StrapReportModel r = JSON.fromJson(arr.get(i), reportType);
            rv.add(r);
        }
        return rv;
    }
}
