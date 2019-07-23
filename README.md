## Backend test implemtation
CLI application that parses a custom formatted .log file (csv like) with a list of account transactions data and displays a result summary 

## How to build the application

`./mvnw clean package install -U`

## How to test

`./mvnw test` 

## How to run

`java -jar target/backend-test-1.0.0.jar src/test/resources/account-transactions.log`