package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
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
     * @throws java.io.UnsupportedEncodingException
     * @throws com.strap.sdk.java.StrapResourceNotFoundException
     * @throws com.strap.sdk.java.StrapMalformedUrlException
     */
    public PagedResponse call(String method, Map<String, String> params) throws UnsupportedEncodingException, StrapResourceNotFoundException, StrapMalformedUrlException  {
        Map<String, String> reqParams = new HashMap<>();

        // move url params from params object to url string
        String route = replaceUrlParams(this.uri, params);

        route = paramsToQueryString(route, params);
        reqParams.put("route", route);

        // make request
        HttpRequest res = httpGet(reqParams);

        String numPages = res.header("X-Pages");
        String currentPage = res.header("X-Page");
        String nextPage = res.header("X-Next-Page");

        if(numPages == null || currentPage == null || nextPage == null){
            numPages = currentPage = nextPage = "0";
        } else if("".equals(numPages) || "".equals(currentPage) || "".equals(nextPage)){
            numPages = currentPage = nextPage = "0";
        }
        
        String body = res.body();
        validateResponse(body);
        
        int nPages = Integer.parseInt(numPages);
        int curPage = Integer.parseInt(currentPage);
        int nxPage = Integer.parseInt(nextPage);
        PagedResponse rv = new PagedResponse(body,nPages,curPage,nxPage);
        
        return rv;
    }

    private void validateResponse(String res) throws StrapResourceNotFoundException {
        Map<String, String> resMap = mapFromJSON(res);
        if ((resMap != null
                && resMap.containsKey("success"))
                && "false".equals(resMap.get("success"))) {
            throw new StrapResourceNotFoundException("Requested resource not found.");
        }
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

    private String paramsToQueryString(String url, Map<String, String> params) throws StrapMalformedUrlException {
        String route = url;
        ArrayList<String> opts = this.getOptional();

        // return unchanged url if params not provided or optional list empty
        if (0 >= params.size() || opts == null) {
            return route;
        }

        // get list of allowed, optional parameters
        List<String> allowed = new ArrayList<>();
        for (String param : opts) {
            if (params.containsKey(param)) {
                allowed.add(param + "=" + encodeString(params.get(param)));
            }
        }

        // convert filtered-for-valid parameters to querystring
        if (!allowed.isEmpty()) {
            for (int j = 0, len = allowed.size(); j < len; j++) {
                route += (j == 0 ? "?" : "&") + allowed.get(j);
            }
        }
        // return route with querystring
        return route;

    }

    private String replaceUrlParams(String route, Map<String, String> params) {
        String rv;

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

    private static String encodeString(String name) throws StrapMalformedUrlException {

        if (name == null) {
            return name;
        }

        String rv;
        try {
            rv = java.net.URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            throw new StrapMalformedUrlException();
        }
        return rv;
    }

    private HttpRequest httpGet(Map<String, String> params) {
        return HttpRequest
                .get(params.get("route"))
                .header("X-Auth-Token", this.token);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRequired() {
        return this.required;
    }

    public ArrayList<String> getOptional() {
        return this.optional;
    }

    public void setRequired(ArrayList<String> arrayList) {
        this.optional = arrayList;
    }

    public void setOptional(ArrayList<String> arrayList) {
        this.required = arrayList;
    }

}
