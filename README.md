# strap-sdk-java
strap-sdk-java
### Usage
```java
  StrapSDK strap = new StrapSDK("32ytsthiu93673ohgjlr3974y");
  Map<String, String> params = new HashMap<>();
  // fill parameters for request
  params.put("id", "32ytsthiu93673ohgjlr3974y");
  try {
      strap.report.get(params);
  } catch (Exception ex) {
      System.out.println(ex.getMessage());
  }
```
