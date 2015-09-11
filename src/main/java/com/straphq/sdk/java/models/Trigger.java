package com.straphq.sdk.java.models;

import java.util.ArrayList;

public class Trigger extends StrapModel {

    public String trigger;
    public ArrayList<TriggerUser> users;

    public class TriggerUser {
        public String user;
        public String report;
    }

}
