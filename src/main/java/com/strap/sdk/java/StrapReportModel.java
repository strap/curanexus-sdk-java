package com.strap.sdk.java;

import java.util.ArrayList;

/**
 *
 * @author marcellebonterre
 */
public class StrapReportModel {
    public String id;
    public long timestamp;
    public String date;
    public String type;
    public String guid;
    public ArrayList<String> fired;
    public StrapBody body;
    public StrapFood food;
    public StrapSleep sleep;
    public StrapAggregate activity;
    public StrapAggregate average;
    public ArrayList<StrapAggregate> components;
}