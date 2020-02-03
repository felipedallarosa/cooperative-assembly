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

## Decisões de arquitetura:

Para esse projeto foram escolhidas as sequintes tecnologias:
- Na mensageria: o "RabbitMQ" pela simplicidade de implementação.
- No banco de dados: o "Oracle-xe" pela sua robustes e performance, aguentando perfeitamento grande fluxos de dados. (Entretanto, caso a empresa não tenha licença, pode ser trocado facilmente por um MongoDB ou MySQL)
- No versionamento de banco de dados: o "liquibase" pela sua adaptabilidade em diversos database.
- No versionamento de código: o "GitHub" pela rastreabilidade de alterações entre outros.
- Na criação de classe de dados: o "lombok" pela sua limpeza de código proporcionada.
- Na documentação: o "Swagger" para gerar uma documentação do código.
- Na inspeção do código: o "Sonar" para uma inspeção contínua da qualidade do código.
- No teste unitário: o "Junit", "Mock", "Mockito" para assegurar o funcionamento do código de forma aderente as regras de negócio.
- No teste de performance: o "JMeter" para validar o desempenho da votação.
- Na cobertura de testes: o "Jacoco" para análise da cobertura de testes pois integra facilmente com o Sonar.

Outras decisões de arquitetura
- No versionamento da aplicação: As "Controller" possuem versionamento via URL.
- Para fechamento de sessões de votações e envio para a mensageria foi escolhido o "Scheduller" do próprio SpringBoot.
- O App deverá ter resposta multi idioma, conforme a localização.
- As classes nativas de dados não poderão serem expostas externamente.
- Os dados externos, vindo das controllers, deverão ser validados e adaptados antes de serem enviados aos Services.

Para Performance
- Na v1 foi utilizado a otimização de código.

- Para evitar um over engineering, foi deixado para uma v2 as seguintes sugestões:
-- Criação de indices no banco de dados.
-- Os dados transitados na fila deverão estar no formato JSON.
-- Os serviços serão assincronos, sendo que as requisições vindas do controllerV2, passaram pelo adapter e o adapter irá postar em na fila "Rabbit". (Para gerenciamento do processamento, será utilizado o Reddis)
-- No serviço de Voto, o serviço Externo de verificação de cpf, poderá ser paralelizado (CompletableFuture) com as chamadas de validação alimentadas pelo banco de dados.
-- No serviço de Voto, ao finalizar o processamento já existente, deverá ser atualizado o totalizador daquela sessão de votação no Reddis. 
-- O serviço de geração de resultado, irá ser alimentado dos dados vindos do Reddis.

Pontos para discussões DEVOPS
-- A segurança deverá ser revista, devendo ser adicionado alguma autenticação segura tipo "oauth2"
-- Adicionar um Vault/Wallet para a guarda das senhas.
-- Adicionar o Consul para centralização de configuração por ambiente.
-- Integração com Jaegger/Graylog para centralização de Log.

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