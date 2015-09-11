package com.straphq.sdk.java.models;

import com.google.gson.Gson;

public abstract class StrapModel {

    protected static Gson gson;

    @Override
    public String toString() {
        if(gson == null)
            gson = new Gson();

        return gson.toJson(this);
    }

}
