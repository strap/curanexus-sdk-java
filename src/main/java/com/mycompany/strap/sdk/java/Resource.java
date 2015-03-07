/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.strap.sdk.java;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marcellebonterre
 */
public class Resource {

    private static final Gson JSON = new Gson();

    public String name;
    public String token;
    public String method;
    public String uri;
    public String description;
    public ArrayList<String> required;
    public ArrayList<String> optional;

    public String setToken(String token) {
        this.token = token;
        return this.token;
    }

    public String setMethod(String method) {
        this.method = method;
        return this.method;
    }

    public String call(String method, Map<String, String> params) throws Exception {
        Map<String, String> reqParams = new HashMap<>();

        String route = this.uri;

        String regex = "\\{(\\S+?)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(route);

        StringBuffer strBuf = new StringBuffer();

        int i = 1;
        while (m.find()) {
            String UrlParam = m.group(i);

            if (params.get(UrlParam) != null) {
                String UrlParamVal = params.get(UrlParam);
                m.appendReplacement(strBuf, UrlParamVal);
                params.remove(UrlParam);
                i++;
            } else {
                if (!"GET".equals(this.method)) {
                    throw new Exception("Missing parameter: " + UrlParam);
                } else {
//                    GET calls may omit url params
                    m.appendReplacement(strBuf, "");
                }
            }
        }
        m.appendTail(strBuf);
        route = strBuf.toString();

        if ("GET".equals(this.method)) {

            List<String> allowed = new ArrayList<>();
            for (String param : this.optional) {
                if (params.get(param) != null) {
                    allowed.add(param + "=" + encodeString(params.get(param)));
                }
            }

            if (!allowed.isEmpty()) {
                String query = String.join("&", allowed);
                route += "?" + query;
            }
        } else {
            Type resourceMapType = new TypeToken< Map<String, String>>() {
            }.getType();
            String body = JSON.toJson(params, resourceMapType);
            reqParams.put("body", body);
        }
        reqParams.put("route", route);

        String rv = "";
        switch (method) {
            case "GET":
                rv = httpGet(reqParams);
                break;
            case "PUT":
                rv = httpPut(reqParams);
                break;
            case "POST":
                rv = httpPost(reqParams);
                break;
            case "DELETE":
                rv = httpDelete(reqParams);
                break;
        }
        return rv;
    }

    private static String encodeString(String name) throws NullPointerException {
        String tmp = null;

        if (name == null) {
            return null;
        }

        try {
            tmp = java.net.URLEncoder.encode(name, "utf-8");
        } catch (Exception e) {
        }

        if (tmp == null) {
            throw new NullPointerException();
        }

        return tmp;
    }

    private String httpGet(Map<String, String> params) {
        return HttpRequest
                .get(params.get("route"))
                .header("X-Auth-Token", this.token)
                .body();
    }

    private String httpPut(Map<String, String> params) {
        return HttpRequest
                .put(this.uri)
                .header("X-Auth-Token", this.token)
                .send(params.get("body"))
                .body();
    }

    private String httpPost(Map<String, String> params) {
        return HttpRequest
                .post(this.uri)
                .header("X-Auth-Token", this.token)
                .send(params.get("body"))
                .body();
    }

    private String httpDelete(Map<String, String> params) {
        return HttpRequest
                .delete(params.get("route"))
                .header("X-Auth-Token", this.token)
                .body();
    }

}
