# Java > Strap Server-Side SDK

Strap Server-Side SDK provides a basic, easy to use API for interacting with our API services.

Strap Server-Side SDK keys off of a global API discovery object using the read token for the API.
The Strap Server-Side SDK extracts the need for developers to know, manage, and integrate the API endpoints.

The Project API discovery can be found here:

HEADERS: "X-Auth-Token":
GET [https://api.curanexus.io/discover]([https://api.curanexus.io/discover)

### Installation
Download the following JAR, and include it as one of your project's libraries.
```
curl -O https://s3.amazonaws.com/strap-sdk/strap-sdk-java.jar
```
 Additionally Strap-SDK-Java depends on Google's Json library GSON. Obtain [the JAR](http://search.maven.org/#artifactdetails|com.google.code.gson|gson|2.3.1|jar) from Maven central.
```
http://search.maven.org/#artifactdetails|com.google.code.gson|gson|2.3.1|jar
```

### Initialization

```java
import com.straphq.sdk.java.*;
import com.straphq.sdk.java.models.User;
import com.google.gson.*;

StrapSDK strap = new StrapSDK("Read-Token-Goes-Here");
```

### Basic Usage
```java
// fill map with url parameters and/or http request body key-value pairs
Map<String, String> params = new HashMap<>();
params.put("someKey", "someValue");

try {
// Required params: guid
// Optional params: date, count, start, end
params.put("guid", "my-guid");
StrapResponse res = strap.get("activity", params);
JsonElement data = res.getData(); // Get current page
System.out.println(data);

// Optional params: guid, page, per_page
StrapResponse res = strap.get("today", params);
JsonElement data = res.getData(); // Get current page
System.out.println(data);

// Optional params: guid, page, per_page
StrapResponse res = strap.get("week", params);
JsonElement data = res.getAll(); // get all pages
System.out.println(data);

// Optional params: guid, page, per_page
StrapResponse res = strap.get("month", params);
JsonElement data = res.getData();
System.out.println(data);

// Print all pages of data
while((res = res.getNextPage()) != null) {
    data = res.getData();
    System.out.println(data);
}

// Optional params: platform, count ,page, per_page
StrapResponse res = strap.get("users", params);
JsonElement data = res.getAll();
System.out.println(data);

// Required params: guid
params.put("guid", "my-guid");
StrapResponse res = strap.get("user", params);
JsonElement data = res.getData();
System.out.println(data);

// Optional params: date
StrapResponse res = strap.get("segmentation", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: weekday
StrapResponse res = strap.get("behavior", params);
JsonElement data = res.getData();
System.out.println(data);

// Optional params: date
StrapResponse res = strap.get("trend", params);
JsonElement data = res.getData();
System.out.println(data);

// Optional params: status
StrapResponse res = strap.get("job", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: name
// Optional params: description. guids, clustesr, startDate, endDate, notificationUrl
params.put("name", "My Segmentation");
StrapResponse res = strap.post("job", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
params.put("id", "my-job-id");
strap.delete("job", params);

// Required params: id
params.put("id", "my-job-id");
StrapResponse res = strap.get("job_data", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
params.put("id", "my-report-id");
StrapResponse res = strap.get("report", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
params.put("id", "my-report-id");
StrapResponse res = strap.get("report_food", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
params.put("id", "my-report-id");
StrapResponse res = strap.get("report_workout", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
// Optional params: type
params.put("id", "my-report-id");
StrapResponse res = strap.get("report_raw", params);
JsonElement data = res.getData();
System.out.println(data);


// Optional params: id, key, type, actionType
StrapResponse res = strap.get("trigger", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: active, name, type, range, conditions
// Optional params: actionType, actionURL
StrapResponse res = strap.post("trigger", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
StrapResponse res = strap.delete("trigger", params);
JsonElement data = res.getData();
System.out.println(data);

// Required params: id
// Optional params: page, per_page
StrapResponse res = strap.delete("trigger_data", params);
JsonElement data = res.getAll();
System.out.println(data);

// You can also cast the response to a model class, if one exists.
StrapResponse res = strap.get("users", params);
JsonElement data = res.getAll();

Gson gson = new Gson();
gson.fromJson(data, new ArrayList<User>().getClass());

ArrayList<User> users = gson.fromJson(data, new ArrayList<User>().getClass());
System.out.println(users);
System.out.println(users[0].guid);

} catch (StrapInvalidRequestException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```
