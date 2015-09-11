package com.straphq.sdk.java.models;

import java.util.ArrayList;

public class Report extends StrapModel {
    public String id;
    public long timestamp;
    public String date;
    public String type;
    public String guid;
    public Body body;
    public Food food;
    public Sleep sleep;
    public Activity activity;
    public Activity average;
    public ArrayList<Activity> components;
}