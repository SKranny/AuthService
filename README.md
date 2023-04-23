# Java Spring project "Social networking site"
### Auth-service

## Description
Auth-service is responsible for authenticating new users and their visiting sessions.
Here the user receives his authorization token to use all the features provided by our social network according to his role. We have implemented roles such as: 
- User
- Admin
- Moderator   
## Service technologies
- Java version 11
- Spring Framework
- Flyway
- Lombok
- Spring Data JPA
- Redis
- Spring Security
- Spring Cloud OpenFeign
- Spring Cloud Netflix Eureka
- JWT(JsonWebToken)
- Swagger OpenApi
- Thymeleaf
- JUnit
## Technical description
### How to run the application on your device:
1. (Pre-configuring the PostgreSQL database) Specify in the application.yaml file, or in the environment variables in your IDE, the required application configuration parameters to run:
    - KAFKA_HOST(The address of the Kafka broker. The default host is localhost:9092. Replace it if you are not going to use the default)
    - SERVER_PORT (The port of your application. Specify it manually if you are not going to use the default port: 8082)
    - SECRET_KEY (Your application's secret key. This is needed to protect your service which uses JWT technology)
    - LIFE_TIME (This is the lifetime of your token, stated in milliseconds)
    - MAIL_LOGIN (This is the mail login for the app. Host: smtp.gmail.com)
    - MAIL_PASSWORD (This is the email password for the app, which is given by google)
    - EUREKA_URI (Address of your Eureka server. Specify it if you are not going to use the default address: http://localhost:8081/eureka)
    - REDIS_URL (This is the address of your Redis database. Specify it manually if you are going to use the default: redis://localhost:6000)
2. Run the file AuthApplication.java.
