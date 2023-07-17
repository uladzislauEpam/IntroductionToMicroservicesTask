FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/EurekaServer.jar appE.jar
VOLUME /tmp
EXPOSE 8761
ENTRYPOINT ["java","-jar","/appE.jar"]