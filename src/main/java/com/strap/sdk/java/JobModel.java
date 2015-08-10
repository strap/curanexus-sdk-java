package com.strap.sdk.java;

/**
 *
 * @author Matt Johnson
 */
public class JobModel {

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

    public JobLogModel[] logs;


    public class JobLogModel {

        public String status;
        public String updatedAt;

    }

}
