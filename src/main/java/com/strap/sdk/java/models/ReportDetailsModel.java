package com.strap.sdk.java.models;

import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

/**
 * @author Matt Johnson
 */
public class ReportDetailsModel {

    public String id;
    public long timestamp;
    public String reportId;
    public String type;


    public LinkedTreeMap<String, Object> summary;
    public LinkedTreeMap<String, Object> details;

}
