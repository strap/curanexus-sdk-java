package com.strap.sdk.java;

import com.strap.sdk.java.models.ReportModel;

import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class Report extends Response<ReportModel> {

    public Report(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data);
    }

}
