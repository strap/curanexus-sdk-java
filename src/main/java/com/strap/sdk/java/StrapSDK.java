package com.strap.sdk.java;

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
public class StrapSDK extends StrapSDKBase {

    public StrapSDK(String token) {
        super(token);
    }

    public StrapReportList getActivity(Map<String, String> params) {
        StrapResponse<String> res = super.call("activity", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(rs, res.error);
        return rv;
    }

    public StrapReport getReport(Map<String, String> params) {
        StrapResponse<String> res = super.call("report", "GET", params);
        Type reportType = new TypeToken<StrapReportModel>() {
        }.getType();
        StrapReport rv;
        try {
            StrapReportModel r = super.JSON.fromJson(res.data, reportType);
            rv = new StrapReport(r, res.error);
        } catch (JsonParseException e) {
            rv = new StrapReport(null, res.data);
        }
        return rv;

    }

    public StrapReportList getToday() {
        Map<String, String> params = new HashMap<>();
        return this.getToday(params);
    }

    public StrapReportList getToday(Map<String, String> params) {
        StrapResponse<String> res = super.call("today", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(this, "today", params, rs, res.error);
        rv.numPages = res.numPages;
        rv.currentPage = res.currentPage;
        rv.nextPage = res.nextPage;
        return rv;
    }

    public StrapReportList getWeek() {
        Map<String, String> params = new HashMap<>();
        return this.getWeek(params);
    }

    public StrapReportList getWeek(Map<String, String> params) {
        StrapResponse<String> res = super.call("week", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(this, "week", params, rs, res.error);
        rv.numPages = res.numPages;
        rv.currentPage = res.currentPage;
        rv.nextPage = res.nextPage;
        return rv;
    }

    public StrapReportList getMonth() {
        Map<String, String> params = new HashMap<>();
        return this.getMonth(params);
    }

    public StrapReportList getMonth(Map<String, String> params) {
        StrapResponse<String> res = super.call("month", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(this, "month", params, rs, res.error);
        rv.numPages = res.numPages;
        rv.currentPage = res.currentPage;
        rv.nextPage = res.nextPage;
        return rv;
    }

    public StrapUserList getUsers() {
        Map<String, String> params = new HashMap<>();
        return this.getUsers(params);
    }

    public StrapUserList getUsers(Map<String, String> params) {
        StrapResponse<String> res = super.call("users", "GET", params);

        JsonParser parser = new JsonParser();

        JsonArray arr = parser.parse(res.data).getAsJsonArray();

        ArrayList<Map<String, String>> us = new ArrayList<>();
        for (int i = 0, numObjs = arr.size(); i < numObjs; i++) {
            Type userType = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> u = super.JSON.fromJson(arr.get(i), userType);
            us.add(u);
        }
        StrapUserList rv = new StrapUserList(us, res.error);
        return rv;
    }

    public StrapTrigger getTrigger(Map<String, String> params) {
        StrapResponse<String> res = super.call("trigger", "GET", params);
        Type trigType = new TypeToken<StrapTriggerModel>() {
        }.getType();
        StrapTrigger rv;
        try {
            String r = super.JSON.fromJson(res.data, trigType);
            rv = new StrapTrigger(r, res.error);
        } catch (JsonParseException e) {
            rv = new StrapTrigger(res.data, res.data);
        }
        return rv;
    }

    protected ArrayList<StrapReportModel> jsonToReportList(String jsonStr) {
        JsonParser parser = new JsonParser();

        JsonArray arr = parser.parse(jsonStr).getAsJsonArray();

        ArrayList<StrapReportModel> rv = new ArrayList<>();
        for (int i = 0, numObjs = arr.size(); i < numObjs; i++) {
            Type reportType = new TypeToken<StrapReportModel>() {
            }.getType();
            StrapReportModel r = super.JSON.fromJson(arr.get(i), reportType);
            rv.add(r);
        }
        return rv;
    }
}
