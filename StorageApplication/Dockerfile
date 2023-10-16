FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/StorageApplication.jar appSt.jar
VOLUME /tmp
EXPOSE 8088
ENTRYPOINT ["java","-jar","/appSt.jar"]