/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strap.sdk.java;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapUserList {

    public ArrayList<Map<String, String>> data;
    public String error;

    public StrapUserList(ArrayList<Map<String, String>> data) {
        this.data = data;
        this.error = "";

    }

    public StrapUserList(ArrayList<Map<String, String>> data, String error) {
        this.data = data;
        this.error = error;
    }
}
