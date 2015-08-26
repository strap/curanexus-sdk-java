package com.strap.sdk.java;

import com.google.gson.reflect.TypeToken;
import com.strap.sdk.java.models.TriggerModel;

import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class Trigger extends Response<TriggerModel> {

    public Trigger(StrapSDK strap, String service, Map<String, String> params, PagedResponse data) {
        super(strap, service, params, data, new TypeToken<TriggerModel>(){});
    }

}
