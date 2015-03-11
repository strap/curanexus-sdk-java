# strap-sdk-java
strap-sdk-java
### Usage
```java
  StrapSDK strap = new StrapSDK("QNIODsXElu3W7Csg452ge212GWQ0zjS2W3");
  Map<String, String> params = new HashMap<>();
  params.put("guid", "someGuid");
  StrapResponse res = strap.activity.get(params);
  System.out.println(res.body);
  System.out.println(res.error);
```
