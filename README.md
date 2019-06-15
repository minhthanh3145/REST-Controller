# REST-Controller
A Spring Boot application that implements basic functionalities of a REST service

## Prerequisite
- An IDE that can import a maven project. 
- Java 8.0.

## Instruction to run the application
- Import the project into your IDE, the maven dependencies will be automatically downloaded. 
- Start the application once the dependencies are loaded. 
- Send requests to localhost:8000 with the approrpriate endpoints.

## Instruction to run the integration test
- Open ChargingSessionControllerTest which is a Mockito test and start the test.
- You will see sample requests are sent, asserts that assert actual responses against actual responses. 
- The test will pass. 

## Overall architecture

***ApplicationMain** is the starting point of the application. 

**ChargingSessionController** is a Spring Boot Rest Controller. Thanks to the magic provided by Spring Boot, all incoming requests will be mapped to the appropriate endpoints and methods. The controller simply delegates all calls to the facade.

**ChargingSessionFacade** is a facade pattern implementation that simplifies interactions with the underlying complex system. Although the requirement does not specify anything concrete about the implementation, I think it’s a best practice to account for extensibility. Submission of a charging session superficially only includes a save into the database, but business requirements may make it necessary to authenticate the sender, in which case the operation may involve more than two services (Security Service and Charging Sessiong Service) which collectively form what is perceived a charging session submission. Of course, over-abstracting causes unnecessary complexity, so for now the facade only has one service and merely delegates calls to the service.

**ChargingSessionService** is the class that contains most of the business logic. It prepares entities to save into the repository and process the entities returned by the repository.

**ChargingSessionRepository** is an interface whose implementation is provided by JPA’s magic. Under the hood, an entity manager is injected in each thread from the entity manager factories. The entity manager itself is not thread-safe, but the factories are, and since each thread gets its own instances of an entity manager, the repository is generally thread-safe.

Due to the peculiar format of the string representation of the date in the request, I implemented a DateUtil class which contains methods to parse to and from the desired date format. Also inside this class is a custom serializer and deserializer for Jackson library to convert the Date field of entities into the desired string format. 
