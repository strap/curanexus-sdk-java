/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapTrigger {

    public String data;
    public String error;

    public StrapTrigger(String data) {
        this.data = data;
        this.error = "";
    }

    public StrapTrigger(String data, String error) {
        this.data = data;
        this.error = error;
    }
}
