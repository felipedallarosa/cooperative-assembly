# Technical Challenge

## To run the project:
For the environment, the database "oracle-xe" and the message "rabbitmq" are needed, which can be started inside the "\docker" folder with the command below:

`` shell
docker-compose up -d
``
(Make sure that the "docker\oracle\init.sql" file has been executed, as it is responsible for creating the schema and user).

To set up the database structure, "liquibase" was used, which can be started inside the "\database" folder with the commands below:

```shell
#Check the available packs to update the structure
.\database_verbose.ps1

#Update the structure
.\database_update.ps1

#If you need to rollback
. \ database_rollback.ps1
```

To properly start the project

```shell
mvn clean install spring-boot: run
```
## Architectural decisions:

The following technologies were chosen for this project:
- In messaging: "RabbitMQ" for the simplicity of implementation.
- In the database: "Oracle-xe" for its robustness and performance, withstanding large data flows perfectly. (However, if the company does not have a license, it can be easily exchanged for a MongoDB or MySQL)
- Database versioning: the "liquibase" for its adaptability in several databases.
- Code versioning: "GitHub" for tracking changes, among others.
- When creating a data class: the "lombok" for its clean code provided.
- In the documentation: the "Swagger" to generate a documentation of the code.
- On code inspection: "Sonar" for continuous inspection of code quality.
- In the unit test: "Junit", "Mock", "Mockito" to ensure the operation of the code in accordance with the business rules.
- In the performance test: the "JMeter" to validate the voting performance.
- In test coverage: the "Jacoco" for analysis of test coverage as it integrates easily with Sonar.

Other architectural decisions
- In the versioning of the application: The "Controller" have versioning via URL.
- To close polling sessions and send messages, SpringBoot's own "Scheduller" was chosen.
- The App must have a multi-language response, depending on the location.
- Native classes of data cannot be exposed externally.
- The external data, coming from the controllers, must be validated and adapted before being sent to the Services.

For Performance
- Code optimization was used.
- Indexes in the database.
- Used CompletableFuture to the External cpf verification.

- To avoid over engineering, the following suggestions were left for a v2:
-- The data carried over in the queue must be in JSON format.
-- The services will be asynchronous, and the requests coming from the controllerV2, passed through the adapter and the adapter will post in the "Rabbit" queue. (For processing management, Reddis will be used)
-- In the Voting service, at the end of the existing processing, the total of that voting session in Reddis must be updated.
-- The result generation service will be fed with data from Reddis.

Points for DEVOPS discussions
- Security should be reviewed, and some secure authentication type "oauth2" must be added
- Add a Vault / Wallet to store passwords.
- Add the Consul to centralize configuration by environment.
- Integration with Jaegger / Graylog for centralization of Log.

## Goal: 
In cooperatives, each member has one vote and decisions are taken in assemblies, by vote. 
From there, you need to create a back-end solution to manage these voting sessions.

This solution must be executed in the cloud and promote the following functionalities through a REST API:

● Register a new agenda; 

● Open a voting session on an agenda (the voting session must be open for a specified time in the opening call or 1 minute by default); 

● Receive votes from members on agendas (votes are only 'Yes' / 'No'. Each member is identified by a unique id and can vote only once per agenda); 

● Count the votes and give the result of the vote on the agenda.

For exercise purposes, the security of the interfaces can be abstracted and any call to the interfaces can be considered as authorized.

The choice of language, frameworks and libraries is free (as long as it does not infringe use rights).

It is important that the agendas and votes are persisted and that they are not lost with the application restart.

###Bonus tasks
Bonus tasks are not mandatory, but they do allow us to evaluate other knowledge you may have. We always suggest that the candidate consider and present how far he can do, considering his level of knowledge and the quality of delivery.

####Bonus Task 1 - Integration with external systems 
Integrate with a system that verifies, from the member's CPF, if he can vote 

GET https://user-info.herokuapp.com/users/{cpf} 

If the CPF is invalid, the API will return HTTP Status 404 (Not found). You can use CPF generators to generate valid CPFs; 

If the CPF is valid, the API will return if the user can (ABLE_TO_VOTE) or cannot (UNABLE_TO_VOTE) perform the operation

####Bonus task 2 - Messaging and queues
The result of the vote needs to be reported to the rest of the platform, this should preferably be done through messaging. When the voting session closes, post a message with the result of the vote.

####Bonus Task 3 - Performance 
Imagine that your application can be used in scenarios that have hundreds of thousands of votes. It must behave in a performative manner in these scenarios; 
- Performance tests are a good way to guarantee and observe how your application behaves.

####Bonus Task 4 - API Versioning
How will you do the version of your application's API? What strategy to use?

##What will be analyzed
- Simplicity in solution design (avoid over engineering) 
- Code organization 
- Project architecture 
- Good programming practices (maintainability, readability, etc.) 
- Possible bugs 
- Handling errors and exceptions 
- Brief explanation of why the choices were made during development of the solution 
- Use of automated tests and quality tools 
- Code cleaning 
- Documentation of the code and API 
- Application logs 
- Messages and organization of commits

##Important Notes
- Do not start the test without solving all doubts 
- We will run the application to test it, take care of any external dependencies and make it clear if there are special instructions for running it 
- Test your solution well, avoid bugs