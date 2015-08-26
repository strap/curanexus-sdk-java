package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.strap.sdk.java.models.ReportModel;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class ReportList extends Response<List<ReportModel>> {

    private final Gson JSON = new Gson();

    public ReportList(StrapSDK strap, String service, Map<String, String> params, PagedResponse res) {
        super(strap, service, params, res, new TypeToken<List<ReportModel>>(){});
    }

    public ReportList getAll() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {        
        return this.getPages(super.getPageData().getNumPages());
    }

    public ReportList getPages(int numPages) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
       return getPages(numPages,super.getPageData().getDefaultPerPage());
    }

    public ReportList getPages(int numPages, int perPage) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
         // make requests for desired num pages
        List<PagedResponse> res = super.manualGetPages(numPages, perPage);
        
        // replace this report list with result of manual call
        List<ReportModel> empty = new ArrayList<>();
        this.setData(empty);
        
        for (PagedResponse r : res) {
            // parse response into T-type
            Type t = new TypeToken<List<ReportModel>>() {}.getType();
            List<ReportModel> items = JSON.fromJson(r.getData(), t);
            this.getData().addAll(items);
        }
        return this;
    }

    

}
