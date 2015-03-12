package com.strap.sdk.java;

import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapReportModel {

    public String id;
    public long timestamp;
    public String date;
    public String type;
    public String range;
    public int count;
    public String guid;
    public StrapAggregate aggregate;
    public Map<String,Object> details;
    public Object Average;
}
