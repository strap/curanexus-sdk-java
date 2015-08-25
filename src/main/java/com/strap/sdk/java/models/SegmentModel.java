package com.strap.sdk.java.models;

import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

/**
 * @author Matt Johnson
 */
public class SegmentModel {

    public SegmentActivityModel[] activity;
    public SegmentBodyModel[] body;
    public SegmentSleepModel[] sleep;
    public SegmentFoodModel[] food;


    public class SegmentActivityModel {

        public AggregateModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public LinkedTreeMap<String, Integer> mobile;
        public LinkedTreeMap<String, Integer> platforms;
        public LinkedTreeMap<String, Integer> triggers;

    }

    public class SegmentBodyModel {

        public BodyModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public LinkedTreeMap<String, Integer> mobile;
        public LinkedTreeMap<String, Integer> platforms;
        public LinkedTreeMap<String, Integer> triggers;

    }

    public class SegmentSleepModel {

        public SleepModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public LinkedTreeMap<String, Integer> mobile;
        public LinkedTreeMap<String, Integer> platforms;
        public LinkedTreeMap<String, Integer> triggers;

    }


    protected class SegmentFoodModel {

        public FoodModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public LinkedTreeMap<String, Integer> mobile;
        public LinkedTreeMap<String, Integer> platforms;
        public LinkedTreeMap<String, Integer> triggers;

    }


    public class ChartModel {

        public Object[][] data; // date, value
        public String human;
        public String name;


    }

    public class GuidModel {

        public String guid;
        public LinkedTreeMap<String, Integer> triggers;

    }

}
