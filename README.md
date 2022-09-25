# Medication delivery system

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.6](https://maven.apache.org)

## Running the application locally

There are several ways to run drone delivery application on your local machine. One way is to execute the `main` method in the `com.musala.dronedelivery.DroneDeliveryApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Check application status
Additional features that help us to monitor and manage the Spring Boot application

**_All actuator endpoints_**
http://localhost:8080/actuator

**_To check application health_**
http://localhost:8080/actuator/health

## OpenAPI specification 

http://localhost:8080/swagger-ui/index.html

## Features:

The service should allow:

 | Feature                                                  | Status      |
|----------------------------------------------------------|-------------|
| Registering a drone                                      | Implemented |
| Loading a drone with medication items                    | TBC         |
| Checking loaded medication items for a given drone       | TBC         |
| Checking available drones for loading                    | Implemented |
| Check drone battery level for a given drone              | Implemented |
| Introduce a periodic task to check drones battery levels | Implemented |
| JUnit Test                                               | TBC         |

## Tech Stack

Java 17, SpringBoot, H2 Database, Hibernate, Maven 3, OpenAPI(Swagger), Actuator,

**Server:** Embedded Apache Tomcat

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
