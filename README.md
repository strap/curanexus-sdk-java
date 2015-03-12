# strapSDK

Strap SDK Java provides an easy to use, chainable API for interacting with our API services. Its purpose is to abstract away resource information from our primary API, i.e. not having to manually track API information for your custom API endpoint.

Strap SDK Java keys off of a global API discovery object using the read token for the API. The Strap SDK Java extracts the need for developers to know, manage, and integrate the API endpoints.

The a Project API discovery can be found here:

HEADERS: "X-Auth-Token": GET https://api2.straphq.com/discover

Once the above has been fetched, strapSDK will fetch the API discover endpoint for the project and build its API.

### Installation

```
git clone git@github.com:strap/strap-sdk-java.git
```

### Usage
```java
  // initialize Strap SDK with read token
  StrapSDK strap = new StrapSDK("QNIODsXElu3W7Csg452ge212GWQ0zjS2W3");
 
  // fill map with url parameters and/or http request body key-value pairs
  Map<String, String> params = new HashMap<>();
  params.put("someKey", "someValue");
 
  // make request for data based on params
  StrapReportList activities = strap.getActivity(params);
  System.out.println(activities.body);
  System.out.println(activities.error);

  StrapReportList today = strap.getToday(params);
  System.out.println(today.body);
  System.out.println(today.error);

  StrapUserList users = strap.getUsers(params);
  System.out.println(users.body);
  System.out.println(users.error);

  StrapReport report = strap.getReport(params);
  System.out.println(report.body);
  System.out.println(report.error);

  StrapTrigger trigger = strap.getTrigger(params);
  System.out.println(trigger.body);
  System.out.println(trigger.error);
```

