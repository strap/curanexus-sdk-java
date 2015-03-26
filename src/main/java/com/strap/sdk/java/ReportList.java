package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class ReportList extends Response<List<ReportModel>> {
    private final Gson JSON = new Gson();

    public ReportList(StrapSDK strap, String service, Map<String, String> params, PagedResponse res) {
        super(strap, service, params, res);
    }

    public ReportList getAll() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        while (this.hasNext()) {
            // request next page of data
            PagedResponse res = super.next();
            // parse resposne into T-type
            ReportModel item = this.convertToReportModel(res.getData());
            super.getData().add(item);
        }
        super.getPageData().setCurrentPage(1);
        return this;
    }

    private ReportModel convertToReportModel(String data) {
        Type t = new TypeToken<Report>() {}.getType();
        return JSON.fromJson(data, t);
    }
}
