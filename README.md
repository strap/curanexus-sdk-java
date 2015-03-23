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

### Usage
```java
  import com.strap.sdk.java.*;
  //
  // Project class definition goes here
  //
  // initialize Strap SDK with read token
<<<<<<< HEAD
  StrapSDK strap = new StrapSDK("Read-Token-Goes-Here");
=======
  StrapSDK strap = new StrapSDK("ReadToken");
>>>>>>> 6043d4c072433f958a6ad3e5e765f94af2db27ab
 
  // fill map with url parameters and/or http request body key-value pairs
  Map<String, String> params = new HashMap<>();
  params.put("someKey", "someValue");
<<<<<<< HEAD

 if (strap.hasError()) {
     System.out.println(strap.error);
 } else {
   // make request for data based on params

    //"optional": 
    //  "guid",
    //  "page",
    //  "per_page"
   StrapReportList today = strap.getToday(params);
   System.out.println(today.data);
   System.out.println(today.error);

    //"optional":
    //  "guid",
    //  "page",
    //  "per_page"
   StrapReportList week = strap.getWeek(params);
   System.out.println(week.data);
   System.out.println(week.error);
   

    //"optional": 
    //  "guid",
    //  "page",
    //  "per_page"
   StrapReportList month = strap.getMonth(params);
   System.out.println(month.data);
   System.out.println(month.error);
   
   ArrayList<StrapReportList> allMonth = strap.getMonth(params).getAll();
   System.out.println(allMonth);
   
   ArrayList<StrapReportList> mth = new ArrayList<>();
   while (strap.getMonth(params).hasNext()) {
       mth.add(strap.getMonth(params).next());
   }
   System.out.println(mth);
   
   //"optional": 
   //   "platform",
   //   "count",
   //   "page",
   //   "per_page"
   StrapUserList users = strap.getUsers(params);
   System.out.println(users.data);
   System.out.println(users.error);
   
   ArrayList<StrapReportList> allUsers = strap.getUsers(params).getAll();
   System.out.println(allUsers);
   
    // "required":"triggerId",
    // "optional": 
    //  "page",
    //  "per_page"
   StrapTrigger trigger = strap.getTrigger(params);
   System.out.println(trigger.data);
   System.out.println(trigger.error);
   
   ArrayList<StrapReportList> allTrig = strap.getTrigger(params).getAll();
   System.out.println(allTrig);
   
    //"required": "guid"
    //"optional":
    //  "date",
    //  "count",
    //  "start",
    //  "end"
   params.put("guid", "brian-fitbit");
   StrapReportList activities = strap.getActivity(params);
   System.out.println(activities.data);
   System.out.println(activities.error);
   
   //"required":"id"
   // params.put("id", "ID-GOES-HERE");
   StrapReport report = strap.getReport(params);
   System.out.println(report.data);
   System.out.println(report.error);
}
```

