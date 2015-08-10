package com.strap.sdk.java.models;

import java.util.HashMap;

/**
 * @author Matt Johnson
 */
public class ReportDetailsModel {

    public String id;
    public long timestamp;
    public String reportId;
    public String type;


    public HashMap<String, Object> summary;
    public HashMap<String, Object> details;


    public class DetailsModel {

    }

}
