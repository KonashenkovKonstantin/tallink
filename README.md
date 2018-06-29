# tallink
Implementation of a test task for Tallink

## Requirements
- Java 8  
- Maven 3  

## How to configure
This solutions doesn't have any configuration properties. The solution doesn't need extrenal application servers and databases.  
Embeded Jetty application server and Derby in-memory database were used for sake of test and simplicity to check this solution.

## How to build solution
Go to 'tallink/TallinkTestTask/' and  run maven command 'mvn clean install'.   
JUnit tests will be run (unit tests and functional test ) and a 'tallink/TallinkTestTask/target' folder will be created.  
The target folder contains TallinkTask.war, RunStandaloneApplication.jar

## How to run solution
There are two options, you can choose whatever you prefer:
- build solution  
- run application, you have two options:  
- - double click on RunStandAloneApplication.jar file
- - java -jar RunStandAloneApplication.jar
-open http://localhost:8088/ link in browser

## How to run additional system test
To run system test you need to open this link http://localhost:8088/tests in browser.  
As a result you will information about failed test or about successful tests
