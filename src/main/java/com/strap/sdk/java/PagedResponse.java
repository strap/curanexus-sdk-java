package com.strap.sdk.java;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class PagedResponse {

    private final String data;
    private int currentPage;
    private final int numPages;
    private final int nextPage;
    
    private final StrapSDK strap;
    private final String service;
    Map<String, String> params;
    

    public PagedResponse(StrapSDK strap, String service, Map<String, String> params,PagedResponse res) {
        this.data = res.getData();
        this.currentPage = res.getCurrentPage();
        this.numPages = res.getNumPages();
        this.nextPage = res.getNextPage();
        
        this.strap = strap;
        this.service = service;
        this.params = this.cloneParams(params);
    }

    public PagedResponse(String data, int curPage, int numPages, int nextPage) {
        this.data = data;
        this.currentPage = curPage;
        this.numPages = numPages;
        this.nextPage = nextPage;
        this.strap = null;
        this.service = null;
        this.params = null;
    }

    public String getData(){
        return this.data;
    }
    
    public int getNumPages(){
        return this.numPages;
    }
    
    public int getNextPage(){
        return this.nextPage;
    }
    
    public int getCurrentPage(){
        return this.currentPage;
    }
    
    public int setCurrentPage(int currentPage){
        this.currentPage = currentPage;
        return this.currentPage;
    }
            
    public boolean hasNext() {
        return (this.numPages > 0 && this.currentPage < this.numPages);
    }

    public PagedResponse next() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        this.currentPage++;
        params.put("page", Integer.toString(this.currentPage));
        return strap.call(this.service, "GET", this.params);
    }
    
    private Map<String, String> cloneParams(Map<String, String> params) {
        Map<String, String> p = new HashMap<>();
        for (Map.Entry pair : params.entrySet()) {
            p.put((String) pair.getKey(), (String) pair.getValue());
        }
        return p;
    }
}
