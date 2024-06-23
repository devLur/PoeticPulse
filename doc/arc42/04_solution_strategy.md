# Solution Strategy

A short summary and explanation of tech decisions and solution strategies, that shape the system architecture.

## Tech Decisions

**Spring Boot**  
The backend application uses the Spring Boot Framework because of its extensive libraries that help to quickly deliver a working application.

**React**  
The frontend application will use the React framework because of its [popularity](https://gist.github.com/tkrotoff/b1caa4c3a185629299ec234d2314e190).

**MongoDB**  
Because relational databases have their fixed schema and tabular structure that do not naturally accommodate hierachical or varied formats, a noSQL Database shall be used. The decision goes to MongoDB due to its coupling with Java and Spring Boot. Spring Boot offers extensive libraries to integrate MongoDB.

**Docker**
Docker is used to simplify and steamline the process of packaging, deploying and managing the applications.

## Architectural pattern decisions

**Domain Model pattern**  
At the core of PoeticPulse is a simple domain model based around the "Poem" entity and its "Poem Lines". The used [Domain Model pattern](https://java-design-patterns.com/patterns/domain-model/) aims to create a conceptual model in the software that matches the real world systems.

**Controller-Service-Repository architecture**  
The [Controller-Service-Repository](https://tom-collings.medium.com/controller-service-repository-16e29a4684e5) architecture shall be used to increase separation of concern.

## Quality Goal decisions

**JaCoCo**  
JaCoCo is used to check test coverage for Java code. Additionally, the software build is set to fail when a test coverage of 90% can not be reached.

**Checkstyle**  
Checkstyle is used to enforce code conventions and formatting. A zero tolerance policy applies. The software build fails when there is one violated rule.

**Sonarqube**  
Sonarqube is used to automatically inspect and analyse the project to detect bugs, security vulnerabilites and code quality issues.

**Spring Boot Actuator**  
The Spring Boot Actuator is used to provide additional health endpoints.

**Wiremock**  
Wiremock is used to mock services to decrease dependency on external services.

**Postman Collections**
Postman Collection test runs are used to measure the response time. If the response time is not acceptable for a live demonstration (greater than 2 sec), the test fails.

**Artillery**  
Artillery is used to do a performance test to measure the systems performance under load.

**Selenium**  
Selenium is used to automate browser interactions with the frontend application.