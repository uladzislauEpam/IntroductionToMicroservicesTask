FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/ApiGateway-1.0-SNAPSHOT.jar appG.jar
VOLUME /tmp
EXPOSE 8000
ENTRYPOINT ["java","-jar","/appG.jar"]