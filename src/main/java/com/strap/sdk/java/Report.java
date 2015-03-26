package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapReport {
   public StrapReportModel data;
   public String error;
   
    public StrapReport(StrapReportModel data) {
        this.data = data;
        this.error = "";

    }

    public StrapReport(StrapReportModel data, String error) {
        this.data = data;
        this.error = error;
    }
}
