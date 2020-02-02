# Technical Challenge

## To run the project:
    

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
Imagine that your application can be used in scenarios that have hundreds of thousands of votes. She must behave in a performative manner in these scenarios; ○ Performance tests are a good way to guarantee and observe how your application behaves.

####Bonus Task 4 - API Versioning
How would you version your application's API? What strategy to use?

##What will be analyzed
- Simplicity in solution design (avoid over engineering) 
- Code organization 
- Project architecture 
- Good programming practices (maintainability, readability, etc.) 
- Possible bugs 
- Handling errors and exceptions 
- Brief explanation of why the choices made during development of the solution 
- Use of automated tests and quality tools 
- Code cleaning 
- Documentation of the code and API 
- Application logs 
- Messages and organization of commits

##Important Notes
- Do not start the test without solving all doubts 
- We will run the application to test it, take care of any external dependencies and make it clear if there are special instructions for running it 
- Test your solution well, avoid bugs