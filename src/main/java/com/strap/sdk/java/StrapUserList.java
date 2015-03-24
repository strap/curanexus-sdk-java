package com.strap.sdk.java;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapUserList extends StrapPagedResponse {

    public ArrayList<StrapUserModel> data;
    public String error;
    private StrapSDK strap;
    private String service;
    Map<String, String> params;

    public StrapUserList(StrapSDK strap, String service, Map<String, String> params, ArrayList<StrapUserModel> data, String error) {
        this.strap = strap;
        this.service = service;
        this.params = params;
        this.data = data;
        this.error = error;
    }

    public StrapUserList(ArrayList<StrapUserModel> data) {
        this.data = data;
        this.error = "";

    }

    public StrapUserList(ArrayList<StrapUserModel> data, String error) {
        this.data = data;
        this.error = error;
    }

    public StrapUserList getAll() {
        while (this.hasNext()) {
            this.data.addAll(this.next().data);
        }
        return this;
    }

    public boolean hasNext() {
        return (super.numPages > 0 && super.currentPage < super.numPages);
    }

    public StrapUserList next() {
        super.currentPage++;
        params.put("page", Integer.toString(super.currentPage));
        
        StrapResponse<String> res = strap.call(this.service, "GET", this.params);

        Type userType = new TypeToken<StrapUserModel>() {
        }.getType();

        ArrayList<StrapUserModel> us = strap.JSON.fromJson(res.data, userType);
        return new StrapUserList(this.strap, this.service, this.params, us, res.error);
    }
}
