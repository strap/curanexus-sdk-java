package com.straphq.sdk.java.models;

import java.util.ArrayList;

public class Job extends StrapModel {

    public String id;
    public String name;
    public String notificationUrl;
    public String description;
    public String startDate;
    public String endDate;
    public String status;
    public String createdAt;
    public String updatedAt;
    public String[] guids;

    public ArrayList<JobLog> logs;

    public class JobLog {

        public String status;
        public String updatedAt;

    }

}
