# pp1.1.5
```
$ mvn test
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running UserServiceTest
Jun 03, 2023 11:11:58 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 5.6.3.Final
Jun 03, 2023 11:11:58 AM org.hibernate.annotations.common.reflection.java.JavaReflectionManager <clinit>
INFO: HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
Jun 03, 2023 11:11:58 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
Jun 03, 2023 11:11:58 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.postgresql.Driver] at URL [jdbc:postgresql://localhost:5432/firstdb]
Jun 03, 2023 11:11:58 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=zer0}
Jun 03, 2023 11:11:59 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
Jun 03, 2023 11:11:59 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
Jun 03, 2023 11:11:59 AM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.PostgreSQL10Dialect
Jun 03, 2023 11:12:00 AM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
[+] User `Ivan` added to db
[+] User `Ivan` added to db
[+] User `Ivan` added to db
[+] User `Ivan` added to db
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.378 sec

Results :

Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.813 s
[INFO] Finished at: 2023-06-03T11:12:01Z
[INFO] ------------------------------------------------------------------------
```