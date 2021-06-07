spring boot web application that runs the Date Warehouse Rest Application.

## About the Application
* Application will store products and it's related articles stock in inventory system. 
   Also will remove product from system in case any product sold and will update article stock accordingly
* All inventory system operations are exposed as REST API. So any front end applications can consume these api's
 and do necessary updates on inventory system
 

## Install & Running
 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
* [Maven 3.8.1](https://maven.apache.org/download.cgi)

### Build & run 
```
$ mvn clean install
```

* Run the web server on dev mode
```
$ mvn spring-boot:run
```

## Built With
* [Spring boot 2.3.5.RELEASE ](https://projects.spring.io/spring-boot/) - Backed Framework
* [Maven](https://maven.apache.org/) - Dependency Management

