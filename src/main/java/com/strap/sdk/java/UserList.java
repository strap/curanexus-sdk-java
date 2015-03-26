package com.strap.sdk.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class UserList extends Response<List<UserModel>> {
    private final Gson JSON = new Gson();

    public UserList(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data);
    }
//
    
    public UserList getAll() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException {
        while (this.hasNext()) {
            // request next page of data
            PagedResponse res = super.next();
            // parse resposne into T-type
            UserModel item = this.convertToUserModel(res.getData());
            super.getData().add(item);
        }
        super.getPageData().setCurrentPage(1);
        return this;
    }

    private UserModel convertToUserModel(String data) {
        Type t = new TypeToken<UserModel>() {}.getType();
        return JSON.fromJson(data, t);
    }
    
}
