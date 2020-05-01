# innso-customer-tracking

## Requirements
* Git
* Java 8
* Maven 3.6

## How to launch

Download the code source from gitlab:
```
git clone https://github.com/quanghuytran33/oneup-recruitment.git
```

Generate package Java with this code:
``` 
mvn clean install -DskipTests
```

Then launch the application
``` 
java -jar target/oneup-recruitment-1.0-SNAPSHOT.jar
```

## How to test

After launching the application, the QA can test the api via Swagger UI 
by connecting to this URL

``
http://localhost:9999/api/swagger-ui.html
``