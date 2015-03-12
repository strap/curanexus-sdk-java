package com.strap.sdk.java;

import java.util.ArrayList;

/**
 *
 * @author marcellebonterre
 */
public class StrapReportList {

    public ArrayList<StrapReportModel> data;
    public String error;

    public StrapReportList(ArrayList<StrapReportModel> data) {
        this.data = data;
        this.error = "";

    }

    public StrapReportList(ArrayList<StrapReportModel> data, String error) {
        this.data = data;
        this.error = error;
    }
}
