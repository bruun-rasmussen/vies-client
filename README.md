vies-client
===========

Java SOAP client for the European "VAT Information Exchange System" (VIES) system for validation of VAT numbers

Latest version available from Maven Central as:
```xml
  <dependency>
    <groupId>dk.bruun-rasmussen.vies</groupId>
    <artifactId>vies-client</artifactId>
    <version>0.5.1</version>
  </dependency>
```

Building and testing
--------------------

To build the project:
```sh
mvn clean install
```

To run the tests:
```sh
mvn failsafe:integration-test
```

Usage
-----

Example code:

```java
import dk.es.br.vies.*;

ViesVatService service = new ViesVatService();
ViesVatRegistration reg;
try {
    reg = service.lookup("DK", "25472020");
}
catch (ViesVatServiceException ex) {
    // Handle service exception, e.g. network error, service unavailable, ...
}
if (reg == null) {
    // VAT number is invalid
}
else {
    // VAT number is valid
    System.out.println("Name: " + reg.getName());
    System.out.println("Address: " + reg.getAddress());
}
```
