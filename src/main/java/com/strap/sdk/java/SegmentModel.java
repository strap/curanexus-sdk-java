package com.strap.sdk.java;

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

        public HashMap<String, Integer> mobile;
        public HashMap<String, Integer> platforms;
        public HashMap<String, Integer> triggers;

    }

    public class SegmentBodyModel {

        public BodyModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public HashMap<String, Integer> mobile;
        public HashMap<String, Integer> platforms;
        public HashMap<String, Integer> triggers;

    }

    public class SegmentSleepModel {

        public SleepModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public HashMap<String, Integer> mobile;
        public HashMap<String, Integer> platforms;
        public HashMap<String, Integer> triggers;

    }


    protected class SegmentFoodModel {

        public FoodModel averages;
        public ChartModel[] chart;
        public GuidModel[] guids;

        public int clusterId;

        public HashMap<String, Integer> mobile;
        public HashMap<String, Integer> platforms;
        public HashMap<String, Integer> triggers;

    }


    public class ChartModel {

        public Object[][] data; // date, value
        public String human;
        public String name;


    }

    public class GuidModel {

        public String guid;
        public HashMap<String, Integer> triggers;

    }

}
