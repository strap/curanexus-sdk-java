# strapSDK

Strap SDK Java provides an easy to use, chainable API for interacting with our API services. Its purpose is to abstract away resource information from our primary API, i.e. not having to manually track API information for your custom API endpoint.

Strap SDK Java keys off of a global API discovery object using the read token for the API. The Strap SDK Java extracts the need for developers to know, manage, and integrate the API endpoints.

The a Project API discovery can be found here:

HEADERS: "X-Auth-Token": GET https://api2.straphq.com/discover

Once the above has been fetched, strapSDK will fetch the API discover endpoint for the project and build its API.

### Installation
Download the following Jar, and include it as one of your project's libraries.
```
curl -O https://s3.amazonaws.com/strap-sdk/strap-sdk-java.jar
```
### Additionally Strap-SDK-Java depends on Google's Json library GSON. Obtain [the JAR](http://search.maven.org/#artifactdetails|com.google.code.gson|gson|2.3.1|jar) from Maven central.
```
http://search.maven.org/#artifactdetails|com.google.code.gson|gson|2.3.1|jar
```

### Usage
```java
  import com.strap.sdk.java.*;
  import com.google.gson.*;
  //
  // Project class definition goes here
  //
  // initialize Strap SDK with read token
  StrapSDK strap = new StrapSDK("Read-Token-Goes-Here");
  Gson JSON = new Gson();

  // fill map with url parameters and/or http request body key-value pairs
  Map<String, String> params = new HashMap<>();
  params.put("someKey", "someValue");

try {
   // make request for data based on params

    //"optional":
    //  "guid",
    //  "page",
    //  "per_page"
   ReportList today = strap.today(params);
   System.out.println(today.getData());

    //"optional":
    //  "guid",
    //  "page",
    //  "per_page"
   ReportList week = strap.week(params);
   System.out.println(week.getData());


    //"optional":
    //  "guid",
    //  "page",
    //  "per_page"
   ReportList month = strap.month(params);
   System.out.println(month.getData());

   // get number of pages specified
   // example here gets 4 pages of data
   ReportList mth = strap.month(params).getPages(4);
   System.out.println(mth.getData());

   // get number of pages specified, with number of items per page
   // example here is 6 pages of data at 2 items per page
   ReportList amth = strap.month(params).getPages(6,2);
   System.out.println(amth.getData());

   ReportList allMonth = strap.month(params).getAll();
   System.out.println(allMonth);

   //"optional":
   //   "platform",
   //   "count",
   //   "page",
   //   "per_page"
   UserList users = strap.users(params);
   System.out.println(users.getData());

   UserList allUsers = strap.Users(params).getAll();
   System.out.println(allUsers.getData());

    // "required":"triggerId"
   Trigger trigger = strap.trigger(params);
   System.out.println(trigger.getData());

    //"required": "guid"
    //"optional":
    //  "date",
    //  "count",
    //  "start",
    //  "end"
   params.put("guid", "guid-goes-here");
   ReportList activities = strap.activity(params);
   System.out.println(activities.getData());

   // "required":"id"
   params.put("id", "ID-GOES-HERE");
   StrapReport report = strap.report(params);
   System.out.println(report.getData());

   // "optional":
   //  "id"
   //  "status",
   JobModel[] jobs = strap.jobs(params);
   System.out.println(JSON.toJson(jobs));

   JobRequestModel jobRequest = new JobRequestModel();
   jobRequest.name = "My Job";

   JobModel job = strap.createJob(jobRequest);
   System.out.println(JSON.toJson(jobs));

   // "required":"id"
   JobModel job = strap.updateJob(params, jobRequest);
   System.out.println(JSON.toJson(jobs));

   //"required":"id"
   strap.deleteJob(params);

   //"required":"id"
   SegmentModel jobData = strap.jobData(params);
   System.out.println(JSON.toJson(jobData));

   SegmentModel[] segments = strap.segmentation()

   // "optional":
   //  "date",
   //  "period"
   SegmentModel[] segments = strap.segmentation(params)
   System.out.println(JSON.toJson(segments));

   //"required": "guid"
   // "optional": "weekday"
   BehaviorModel[] behavior = strap.behavior(params)
   System.out.println(JSON.toJson(behavior));

   //"required": "id"
   ReportFoodModel[] food = strap.food(params);
   System.out.println(JSON.toJson(food));

   //"required": "id"
   ReportWorkoutModel[] workout = strap.workout(params);
   System.out.println(JSON.toJson(workout));

   //"required": "id"
   //"optional": "type"
   ReportDetailsModel[] details = strap.reportDetails(params);
   System.out.println(JSON.toJson(details));

   // "required": "guid"
   // "optional": "date"
   LinkedTreeMap<String, Object> trendingData = sdk.trend(params);
   System.out.println(JSON.toJson(trendingData));

} catch (StrapResponseParseException |
          StrapResourceNotFoundException |
          UnsupportedEncodingException |
          StrapMalformedUrlException ex) {
    Logger.getLogger(TestSDK.class.getName()).log(Level.SEVERE, null, ex);
}
```
