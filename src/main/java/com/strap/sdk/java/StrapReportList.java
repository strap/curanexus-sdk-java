/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
