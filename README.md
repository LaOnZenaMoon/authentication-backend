# authentication-backend
* Used as an authentication server for various purposes

## jwt-api
Handles the user authentication through JWT
* REST API Documentation
* http://{{host}}:8890/swagger-ui.html
* (Optional) Load the initial sample data
* Add the below code in application.yml
```
lozm:
  data:
    enabled: true
```
* Authorization based on URL or HTTP Method

## oauth-api
Handles the user authentication through OAUTH
* REST API Documentation

## core
* common entities and utils

## skills
* Java 1.8
* Spring Boot
* Spring Security
* JPA, Querydsl
* Swagger
* Junit5
* JWT, HttpSession(will be developed), OAUTH
