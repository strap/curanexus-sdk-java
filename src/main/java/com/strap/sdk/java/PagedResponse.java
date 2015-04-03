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
    private int numPages;
    private int nextPage;
    private int perPage;
    private final int defaultPerPage;
    
    private final StrapSDK strap;
    private final String service;
    Map<String, String> params;
    

    public PagedResponse(StrapSDK strap, String service, Map<String, String> params,PagedResponse res) {
        this.defaultPerPage = 10;
        this.data = res.getData();
        this.currentPage = res.getCurrentPage();
        this.numPages = res.getNumPages();
        this.nextPage = res.getNextPage();
        this.perPage = this.defaultPerPage;
        
        this.strap = strap;
        this.service = service;
        this.params = this.cloneParams(params);
    }

    public PagedResponse(String data, int numPages, int curPage, int nextPage) {
        this.defaultPerPage = 10;
        this.data = data;
        this.currentPage = curPage;
        this.numPages = numPages;
        this.nextPage = nextPage;
        this.perPage = this.defaultPerPage;

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
    
    public void setNumPages(int numPages) {
        this.numPages = numPages;
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
    
    public int getDefaultPerPage(){
         return this.defaultPerPage;
    }
          
    public int getPerPage(){
        return this.perPage;
    }
    
    public int setPerPage(int perPage){
        this.perPage = perPage;
        return this.perPage;
    }
    
    public boolean hasNext() {
        return (this.numPages > 0 && this.currentPage <= this.numPages);
    }

    public PagedResponse next() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        params.put("page", Integer.toString(this.currentPage));
        if(this.perPage != this.defaultPerPage)
            params.put("per_page", Integer.toString(this.perPage));
        
        PagedResponse rv = strap.call(this.service, "GET", this.params);
        rv.setPerPage(this.perPage);
        
        this.currentPage++;
        if(this.hasNext())
            this.nextPage++;
        
        return rv;
    }
    
    private Map<String, String> cloneParams(Map<String, String> params) {
        Map<String, String> p = new HashMap<>();
        for (Map.Entry pair : params.entrySet()) {
            p.put((String) pair.getKey(), (String) pair.getValue());
        }
        return p;
    }
}
