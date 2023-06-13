FROM openjdk:17-jdk-slim
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} optimal-path-finder-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/optimal-path-finder-0.0.1-SNAPSHOT.jar"]