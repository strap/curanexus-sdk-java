package com.straphq.sdk.java;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.straphq.sdk.java.models.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StrapResponse {

    private StrapSDK strap;
    private String data;
    private JsonElement json, jsonAll;
    private Header[] headers;
    private Resource resource;
    private Map<String, Object> params;
    private int page;
    private int totalPages;
    private boolean success;
    private String message;
    private Gson gson;

    public static final Map<String, Class> MODELS;

    static {
        HashMap<String, Class> models = new HashMap<>();
        models.put("activity", Report.class);
        models.put("behavior", User.class);
        models.put("food", JsonElement.class);
        models.put("job", Job.class);
        models.put("job_data", JsonElement.class);
        models.put("month", Report.class);
        models.put("report", Report.class);
        models.put("report_food", FoodReport.class);
        models.put("report_workout", WorkoutReport.class);
        models.put("report_raw", JsonElement.class);
        models.put("segmentation", JsonElement.class);
        models.put("today", Report.class);
        models.put("trend", JsonElement.class);
        models.put("trigger", Trigger.class);
        models.put("trigger_data", Trigger.class);
        models.put("user", User.class);
        models.put("users", User.class);
        models.put("week", Report.class);
        MODELS = Collections.unmodifiableMap(models);
    }

    protected StrapResponse(StrapSDK strap, Resource resource, Header[] headers, HttpEntity entity, Map<String, Object> params) throws IOException {

        data = EntityUtils.toString(entity);
        this.headers = headers;
        this.resource = resource;
        this.strap = strap;
        this.params = params;
        success = true;

        gson = new Gson();
        json = gson.fromJson(data, JsonElement.class);


        if(data.charAt(0) == '{') {
            //JsonObject json = gson.fromJson(data, JsonObject.class);
            JsonObject jsonObj = json.getAsJsonObject();

            if(jsonObj.getAsJsonObject().has("success")) {
                success = jsonObj.get("success").getAsBoolean();
            }

            if(jsonObj.getAsJsonObject().has("message")) {
                message = jsonObj.get("message").getAsString();
            }
        }

        for(Header header : headers) {
            if(header.getName().equals("X-Page")) {
                page = Integer.parseInt(header.getValue());
            } else if(header.getName().equals("X-Pages")) {
                totalPages = Integer.parseInt(header.getValue());
            }
        }

    }

    public JsonElement getData() {
        return json;
    }

    public <T> Object getDataAsModel() {

        Class model = MODELS.get(resource.getName());

        if(model != null) {

            if (json.isJsonArray()) {
                return getModelArrayFromClass(model);
            }


            return getModelFromClass(model);

        }


        return null;
    }

    public String getDataAsString() {
        return data;
    }

    public boolean hasNextPage() {
        return page < totalPages;
    }

    private <T> ArrayList<T> getModelArrayFromClass(Class<T> model) {
        return gson.fromJson(json, new ArrayList<T>().getClass());
    }

    private <T> T getModelFromClass(Class<T> model) {
        return gson.fromJson(json, model);
    }


    public StrapResponse getNextPage() throws IOException {
        if(hasNextPage()) {

            if(params == null) {
                params = new HashMap<>();
            }

            params.put("page", page + 1);
            return strap.call(resource, params);
        }

        return null;
    }

    public StrapResponse getPage(int page) throws IOException {

        int limit = 0;

        if(params != null) {
            if(params.containsKey("per_page")) {
                limit = (int) params.get("per_page");
            }
        }

        return getPage(page, limit);
    }

    public StrapResponse getPage(int page, int limit) throws IOException {

        if(page <= totalPages) {

            if (params == null) {
                params = new HashMap<>();
            }

            if (limit > 0) {
                params.put("per_page", limit);
            }

            params.put("page", page);

            return strap.call(resource, params);
        }

        return null;
    }

    public JsonElement getAll() throws IOException {

        if(totalPages <= 1) {
            return json;
        } else if(jsonAll != null) {
            return jsonAll;
        }

        StrapResponse res = this;
        jsonAll = new JsonArray();

        do {

            jsonAll.getAsJsonArray().add(res.getData());

        } while((res = res.getNextPage()) != null);

        return jsonAll;

    }


    public String getMessage() {
        return message;
    }

    public boolean success() {
        return success;
    }

    @Override
    public String toString() {
        return data;
    }

}
