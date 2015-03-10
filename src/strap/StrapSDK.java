package strap;

// Google lib for working with JSON [de]serialization
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public final class StrapSDK {

    private static final Gson JSON = new Gson();
    private static final String discoveryURL = "https://api2.straphq.com/discover";
    private static String token;
    private static Map<String, Resource> resources = new HashMap<>();

    public StrapSDK(String token) {
        StrapSDK.token = token;
        this.discover();
    }

    public void discover() {
//        TODO: exception handling for http response
//        create/perform request to discoveryURL &&
//        set x-auth-token header to this.token
        String services = HttpRequest
                .get(discoveryURL)
                .header("X-Auth-Token", StrapSDK.token)
                .body();

//        save response to resources map using JSON
        Type resourceMapType = new TypeToken< Map<String, Resource>>() {
        }.getType();
        resources = JSON.fromJson(services, resourceMapType);
    }
}
