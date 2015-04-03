package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 * @param <T>
 * @param <R>
 */
public class Response<T> {

    private final Gson JSON = new Gson();

    private T data;
    private PagedResponse pageData;

    public Response(StrapSDK strap, String service, Map<String, String> params, PagedResponse res) {
        pageData = new PagedResponse(strap, service, params, res);
        Type t = new TypeToken<T>() {}.getType();
        this.data = JSON.fromJson(res.getData(), t);
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

    public T setData(T data) {
        this.data = data;
        return this.data;
    }
        
    public PagedResponse next() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        return this.pageData.next();
    }
        
    public boolean hasNext() {
        return this.pageData.hasNext();
    }

    public List<PagedResponse> manualGetPages( int numPages) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
            return this.manualGetPages(numPages, this.pageData.getPerPage());
    }
    
    public List<PagedResponse> manualGetPages( int pagesWanted, int perPage) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        
        if (perPage == 0) {
            perPage = 1;
        }

        this.getPageData().setPerPage(perPage);
        
        List<PagedResponse> rv = new ArrayList<>();
        while (this.hasNext()) {
            // request next page of data
            PagedResponse res = this.next();
            rv.add(res);

            this.getPageData().setNumPages(res.getNumPages());
            
            if(res.getCurrentPage() == pagesWanted)
                break;
        }
        
        return rv;
    }
    
}
