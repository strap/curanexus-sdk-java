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
    public StrapBodyModel body;
    public StrapFoodModel food;
    public StrapSleepModel sleep;
    public StrapAggregateModel activity;
    public StrapAggregateModel average;
    public ArrayList<StrapAggregateModel> components;
}