package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.strap.sdk.java.models.TriggerDataUserModel;
import com.strap.sdk.java.models.TriggerUserModel;
import com.strap.sdk.java.models.UserModel;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TriggerUserList extends Response<List<TriggerDataUserModel>> {

    private final Gson JSON = new Gson();

    public TriggerUserList(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data, new TypeToken<List<TriggerDataUserModel>>(){});
    }

    public TriggerUserList getAll() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        return this.getPages(super.getPageData().getNumPages());
    }

    public TriggerUserList getPages(int numPages) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        return getPages(numPages,super.getPageData().getDefaultPerPage());
    }

    public TriggerUserList getPages(int numPages, int perPage) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        // make requests for desired num pages
        List<PagedResponse> res = super.manualGetPages(numPages, perPage);

        // replace this report list with result of manual call
        ArrayList<TriggerDataUserModel> empty = new ArrayList<>();
      //  this.setData(empty);
        this.setData(null);

        for (PagedResponse r : res) {
            // parse response into T-type
            Type t = new TypeToken<TriggerDataUserModel>() {}.getType();
            TriggerDataUserModel item = JSON.fromJson(r.getData(), t);
            this.getData().add(item);
        }
        return this;
    }
}
