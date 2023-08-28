FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/ResourceProcessor.jar appP.jar
VOLUME /tmp
EXPOSE 8083
ENTRYPOINT ["java","-jar","/appP.jar"]