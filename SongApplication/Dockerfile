FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/SongApplication-1.0-SNAPSHOT.jar appS.jar
VOLUME /tmp
EXPOSE 8090
ENTRYPOINT ["java","-jar","/appS.jar"]