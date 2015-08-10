package com.strap.sdk.java;

import java.util.Map;

/**
 *
 * @author Matt Johnson
 */
public class Job extends Response<JobModel> {

    public Job(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data);

    }

}
