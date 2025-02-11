FROM openjdk:17-alpine
LABEL authors="thedoliver"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]