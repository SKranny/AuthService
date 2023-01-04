FROM openjdk:11
WORKDIR /build
ADD ./target/AuthService*.jar ./auth-service.jar

EXPOSE 8084

CMD java -jar auth-service.jar