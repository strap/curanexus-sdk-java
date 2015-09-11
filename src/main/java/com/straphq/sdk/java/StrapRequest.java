package com.straphq.sdk.java;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import java.io.IOException;
import java.util.Map;

public class StrapRequest {

    private static final String API_AUTH_HEADER = "x-auth-token";
    private static final String CONTENT_TYPE_HEADER = "content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_ENCODING = "UTF-8";

    private CloseableHttpClient client;
    private String token;
    private StrapSDK strap;

    protected StrapRequest(StrapSDK strap, CloseableHttpClient client, String token) {
        this.client = client;
        this.token = token;
        this.strap = strap;
    }

    protected StrapResponse call(Resource resource, Map<String, Object> params, Object data) throws IOException {

        String method = resource.getMethod();

        if(method.equals("GET")) {

            return get(resource, params);

        } else if(method.equals("POST")) {

            return post(resource, params, data);

        } else if(method.equals("PUT")) {

            return put(resource, params, data);

        } else if(method.equals("DELETE")) {

            return delete(resource, params);

        }

        return null;
    }

    private StrapResponse get(Resource resource, Map<String, Object> params) throws IOException {

        HttpGet req = new HttpGet(resource.buildURL(params));
        req.setHeader(API_AUTH_HEADER, token);

        CloseableHttpResponse res = client.execute(req);
        StrapResponse sr = new StrapResponse(strap, resource, res.getAllHeaders(), res.getEntity(), params);
        res.close();

        return sr;

    }

    private StrapResponse post(Resource resource, Map<String, Object> params, Object data) throws IOException {
        HttpPost req = new HttpPost(resource.buildURL(params));
        req.setHeader(API_AUTH_HEADER, token);
        req.setHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON);

        StringEntity se = new StringEntity(data.toString());
        se.setContentEncoding(CONTENT_ENCODING);
        se.setContentType(CONTENT_TYPE_JSON);

        req.setEntity(se);

        CloseableHttpResponse res = client.execute(req);
        StrapResponse sr = new StrapResponse(strap, resource, res.getAllHeaders(), res.getEntity(), params);
        res.close();

        return sr;

    }

    private  StrapResponse put(Resource resource, Map<String, Object> params, Object data) throws IOException {
        HttpPut req = new HttpPut(resource.buildURL(params));
        req.setHeader(API_AUTH_HEADER, token);
        req.setHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON);

        StringEntity se = new StringEntity(data.toString());
        se.setContentEncoding(CONTENT_ENCODING);
        se.setContentType(CONTENT_TYPE_JSON);

        req.setEntity(se);

        CloseableHttpResponse res = client.execute(req);
        StrapResponse sr = new StrapResponse(strap, resource, res.getAllHeaders(), res.getEntity(), params);
        res.close();

        return sr;
    }


    private StrapResponse delete(Resource resource, Map<String, Object> params) throws IOException {
        HttpDelete req = new HttpDelete(resource.buildURL(params));
        req.setHeader(API_AUTH_HEADER, token);

        CloseableHttpResponse res = client.execute(req);
        StrapResponse sr = new StrapResponse(strap, resource, res.getAllHeaders(), res.getEntity(), params);
        res.close();

        return sr;
    }


}
