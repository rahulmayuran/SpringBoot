change application.properties to .yml
to disable whitelabel error, add server.error.whitelebel = false;

in case, if you don't have a proper Db server/url to connect with , then use H2 database configurations

```javascript
spring:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
```
