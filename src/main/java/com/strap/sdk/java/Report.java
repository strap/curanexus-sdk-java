package com.strap.sdk.java;

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
