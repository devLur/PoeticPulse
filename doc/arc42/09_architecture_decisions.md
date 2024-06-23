# Architecture Decisions

## Passing database objects to the frontend

**Problem:**    
the poeticPulse.models.* model classes are used as repository entities to store in the MongoDb and also used in the controller classes to pass responses to the frontend.

**Effect:** 
passing database objects directly to the frontend can expose sensitive data, create security risks and tightly couple the frontend to the database schema, making the system less flexible and harder to maintain.

**Considered Alternatives:**  
Introduce DTO Model classes for the controller and a converter class to convert between DTO Model objects and poeticPulse.model.* class objects.

**Decision:**  
Due to the simplicity of the poeticPulse.models.* classes this Problem can be overlooked at the moment. All classes have a total of 6 fields at the moment, which leaves only a small effect on the maintainability. Additionally, as declared in the Quality Goals and in the Security Concept, Security is not an immediate blocker.

However, this problem is archived as risk and technical debt and is to be adjusted should the poeticPulse.models.* model classes get more complicated

## Use both mock-feign and wiremock to mock external services

**Problem:**  
mock-feign and wiremock are both frameworks to mock external services and are redundant making the application more complex, because a developer needs to build up know how in both frameworks

**Effect:**  
reduced maintainability

**Considered Alternatives:**  
remove wiremock or mock-feign

**Decision:**  
Due to interest in both technologies, both are used. But the problem is archived as technical debt and one technologie is to be removed at a later date
