package com.strap.sdk.java.models;

/**
 * @author Matt Johnson
 */
public class ReportWorkoutModel {

    public String id;
    public String date;
    public String name;
    public String description;
    public String type;
    public String country;
    public String state;
    public String city;
    public String[] startLoc;
    public String[] endLoc;

    public long createdAt;
    public long updatedAt;
    public long startTime;

    public float distance;
    public float avgHeartRate;
    public float maxHeartRate;
    public float avgSpeed;
    public float maxSpeed;
    public float avgTemp;

    public int steps;
    public int calories;
    public int activeMinutes;
    public int nonactiveMinutes;
    public int movingTime;
    public int elapsedTime;


}