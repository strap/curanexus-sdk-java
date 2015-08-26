package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.strap.sdk.java.models.UserModel;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class UserList extends Response<List<UserModel>> {
    private final Gson JSON = new Gson();

    public UserList(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data, new TypeToken<List<UserModel>>(){});
    }
    
    public UserList getAll() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {        
        return this.getPages(super.getPageData().getNumPages());
    }

    public UserList getPages(int numPages) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
       return getPages(numPages,super.getPageData().getDefaultPerPage());
    }

    public UserList getPages(int numPages, int perPage) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
         // make requests for desired num pages
        List<PagedResponse> res = super.manualGetPages(numPages, perPage);
        
        // replace this report list with result of manual call
        List<UserModel> empty = new ArrayList<>();
        this.setData(empty);
        
        for (PagedResponse r : res) {
            // parse resposne into T-type
            Type t = new TypeToken<List<UserModel>>() {}.getType();
            List<UserModel> items = JSON.fromJson(r.getData(), t);
            this.getData().addAll(items);
        }
        return this;
    }
    
}
