# Read Me First

The following was discovered as part of building this project:

* The original package name 'com.ps.parking-lot' is invalid and this project uses 'com.ps.parkinglot' instead.

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](#guides)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#using.devtools)
* [Jersey](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web.servlet.jersey)
* [Validation](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#io.validation)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#actuator)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides

The following guides illustrate how to use some features concretely:

* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

# System requirements

Docker, Mysql server, maven, git and Java 17 needs to preset before running this application.

# Problem Statement

Design a parking lot.

1. On Board a new Parking lot to the system -

<ol style="list-style-type: upper-alpha">

2. When a car arrives at the gate –
a. We need to allocate it the slot number which should be printed on the parking ticket
i. Slot Number Template – [ FloorID:Bay ID ]
b. If a Small car arrives and –
i. If we have a small slot then we should allocate a Small Slot
ii. If No Small slot if free then Medium slot
iii. If No Medium is free then Large slot
iv. If no Large is free then XLarge slot
v. If no XLarge is available then don’t print the slot, print no SLOT FOUND
c. If a Large car arrives then we start with Large then Xlarge
d. If a Medium car arrives, we start with Medium, then large then XLarge
e. If a Large car arrives then Large, followed by Xlarge If no large is free
f. If an Xlarge car arrives then it needs to be Xlarge slot.
The goal is to get the smallest slot that can fit the car.
3. When the car leaves the gate; marked the slot as free to be made available for the next car.

### SetUp

1. Run the Docker and should be running
2. Checkout the project and run `mvn clean install` in root of the project.
3. A docker image will be created on successful build.

# DB Design

![alt](doc/images/parking_lot_DD_Schema.png)

# UML Diagram

![alt](doc/images/parking_lot_classDiagram.png)
