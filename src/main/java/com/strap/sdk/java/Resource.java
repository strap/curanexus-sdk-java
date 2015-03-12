package com.strap.sdk.java;

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

    /**
     *
     * @param method
     * @param params
     * @return
     */
    public StrapResponse<String> call(String method, Map<String, String> params) {
        StrapResponse<String> rv = new StrapResponse<>();
        Map<String, String> reqParams = new HashMap<>();

        // move url params from params object to url string
        String route = replaceUrlParams(this.uri, params);

        route = paramsToQueryString(route, params);
        reqParams.put("route", route);

        String res = httpGet(reqParams);
        return validateResponse(res, rv);

    }

    private StrapResponse<String> validateResponse(String res, StrapResponse<String> rv) {
        Map<String, String> resMap = mapFromJSON(res);
        if ((resMap == null
                || !resMap.containsKey("success"))
                || !"false".equals(resMap.get("success"))) {
            rv.data = res;
            rv.error = "";
        } else {
            rv.data = null;
            rv.error = res;
        }
        return rv;
    }

    private Map<String, String> mapFromJSON(String body) {
        Map<String, String> res;

        Type resourceMapType = new TypeToken< Map<String, String>>() {
        }.getType();
        try {
            res = JSON.fromJson(body, resourceMapType);
        } catch (Exception e) {
            res = null;
        }

        return res;
    }

    private String mapToJSON(Map<String, String> params) {
        Type resourceMapType = new TypeToken< Map<String, String>>() {
        }.getType();
        String body = JSON.toJson(params, resourceMapType);
        return body;
    }

    private String paramsToQueryString(String url, Map<String, String> params) {
        String route = url;

        // get list of allowed, optional parameters
        List<String> allowed = new ArrayList<>();
        for (String param : this.optional) {
            if (params.get(param) != null) {
                allowed.add(param + "=" + encodeString(params.get(param)));
            }
        }

        // convert allowed, optional parameters to querystring
        if (!allowed.isEmpty()) {
            for (int j = 0, len = allowed.size(); j < len; j++) {
                route += (j == 0 ? "?" : "&") + allowed.get(j);
            }

        }
        // return route with querystring
        return route;

    }

    private String replaceUrlParams(String route, Map<String, String> params) {
        String rv = "";

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
                // GET calls may omit url params
                m.appendReplacement(strBuf, "");
            }
        }
        m.appendTail(strBuf);
        route = strBuf.toString();
        rv = route;
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

}
