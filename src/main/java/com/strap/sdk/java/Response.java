package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 * @param <T>
 */
public class Response<T> {

    private final Gson JSON = new Gson();

    private T data;
    private PagedResponse pageData;

    public Response(StrapSDK strap, String service, Map<String, String> params, PagedResponse res) {
        pageData = new PagedResponse(strap, service, params, res);
        this.data = this.convertToT(res.getData());
    }

    public Response(T data) {
        this.data = data;
    }

    public PagedResponse getPageData() {
        return pageData;
    }
    public T getData() {
        return data;
    }

    public PagedResponse next() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        return this.pageData.next();
    }
        
    public boolean hasNext() {
        return this.pageData.hasNext();
    }
    
    private T convertToT(String data){
        Type t = new TypeToken<T>() {}.getType();
        return JSON.fromJson(data, t);
    }
}
