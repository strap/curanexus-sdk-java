package com.strap.sdk.java;

import com.google.gson.JsonParseException;
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

    protected Map<String, String> addPerPage(Map<String, String> params) {
        if (!params.containsKey("per_page")) {
            params.put("per_page", "1");
        }
        return params;
    }

    public StrapReportList getActivity(Map<String, String> params) {
        StrapResponse<String> res = super.call("activity", "GET", params);
        ArrayList<StrapReportModel> rs = jsonToReportList(res.data);
        StrapReportList rv = new StrapReportList(rs, res.error);
        return rv;
    }

    public StrapReport getReport(Map<String, String> params) {
        if (!params.containsKey("id")) {
            return new StrapReport(null, "No id provided");
        }
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
        params = addPerPage(params);
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
        params = addPerPage(params);
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
        params = addPerPage(params);
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
        params = addPerPage(params);
        StrapResponse<String> res = super.call("users", "GET", params);

        Type userType = new TypeToken< ArrayList<StrapUserModel>>() {
        }.getType();

        ArrayList<StrapUserModel> users = super.JSON.fromJson(res.data, userType);
        
        StrapUserList rv = new StrapUserList(this, "month", params, users, res.error);
        rv.numPages = res.numPages;
        rv.currentPage = res.currentPage;
        rv.nextPage = res.nextPage;
        return rv;
    }

    public StrapTrigger getTrigger(Map<String, String> params) {
        params = addPerPage(params);
        StrapResponse<String> res = super.call("trigger", "GET", params);
        
        Type trigType = new TypeToken<StrapTriggerModel>() { }.getType();
        StrapTriggerModel t = super.JSON.fromJson(res.data, trigType);

        StrapTrigger rv = new StrapTrigger(this, "trigger", params, t, res.error);
        rv.numPages = res.numPages;
        rv.currentPage = res.currentPage;
        rv.nextPage = res.nextPage;
        return rv;
    }

    protected ArrayList<StrapReportModel> jsonToReportList(String jsonStr) {
        Type reportType = new TypeToken<ArrayList<StrapReportModel>>() { }.getType();
        ArrayList<StrapReportModel> rv = super.JSON.fromJson(jsonStr, reportType);
        return rv;
    }
}
