FROM openjdk:11
WORKDIR /build
ADD /target/AuthService-0.0.1-SNAPSHOT.jar ./auth-service.jar

EXPOSE 8084

CMD java -jar auth-service.jar