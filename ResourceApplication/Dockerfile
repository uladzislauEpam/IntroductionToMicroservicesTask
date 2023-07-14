FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} appR.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-jar","/appR.jar"]