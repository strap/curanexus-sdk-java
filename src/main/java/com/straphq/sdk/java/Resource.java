package com.straphq.sdk.java;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.straphq.sdk.java.models.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resource {

    private static final String PARAM_PATTERN = "\\{(.*)\\}";

    private String name;
    private String url;
    private String method;
    private ArrayList<String> requiredParams;

    protected Resource(String name, JsonObject json) {

        this.name = name;
        this.url = json.get("uri").getAsString();
        this.method = json.get("method").getAsString();

        this.requiredParams = new ArrayList<>();

        Pattern pattern = Pattern.compile(PARAM_PATTERN);
        Matcher matcher = pattern.matcher(url);

        while(matcher.find()) {

            requiredParams.add(matcher.group(1));

        }
    }

    public URI buildURL() {
        return buildURL(null);
    }

    public URI buildURL(Map<String, Object> params) {


        String uri = url;
        List<NameValuePair> nvp = new ArrayList<>();

        if(params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {

                if (requiredParams.contains(entry.getKey())) {
                    uri = uri.replaceAll("\\{" + entry.getKey() + "\\}", entry.getValue().toString());
                } else {
                    nvp.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }

            }
        }

        try {
            return new URIBuilder(uri).setParameters(nvp).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String getMethod() {
        return this.method;
    }


    public String getName() {
        return this.name;
    }

    public Class getModel() {

        if(name.equals("users")) {
            return new ArrayList<User>().getClass();
        } else if (name.equals("user")) {
            return User.class;
        } else if (name.equals("user")) {
            return User.class;
        } else if(name.equals("activity")) {
            return new ArrayList<Report>().getClass();
        } else if(name.equals("behavior")) {
            return JsonArray.class;
        } else if(name.equals("report")) {
            return Report.class;
        } else if(name.equals("report_details")) {
            return JsonArray.class;
        } else if(name.equals("report_food")) {
            return FoodReport.class;
        } else if(name.equals("report_workout")) {
            return WorkoutReport.class;
        } else if(name.equals("job")) {
        }

        return null;

    }

}
