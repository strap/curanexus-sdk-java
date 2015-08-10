package com.strap.sdk.java.models;

import java.util.ArrayList;

/**
 *
 * @author marcellebonterre
 */
public class ReportModel {
    public String id;
    public long timestamp;
    public String date;
    public String type;
    public String guid;
    public BodyModel body;
    public FoodModel food;
    public SleepModel sleep;
    public AggregateModel activity;
    public AggregateModel average;
    public ArrayList<AggregateModel> components;
}