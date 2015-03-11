package com.strap.sdk.java;

/**
 *
 * @author marcellebonterre
 */
public class StrapModel {

    public String id;
    public int timestamp;
    public String date;
    public String type;
    public String range;
    public int count;
    public String guid;
    public StrapAggregate aggregate;
//		Details   map[string]interface{} `json:"details" bson:"details"`
//		Average   map[string]interface{} `json:"average,omitempty" bson:"average"`
}
