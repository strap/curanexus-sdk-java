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
 * @author Matt Johnson
 */
public class JobList extends Response<List<JobModel>> {
    private final Gson JSON = new Gson();

    public JobList(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data);
    }

    public List<JobModel> getAll() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        return super.getData();
    }


}
