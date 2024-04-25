FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/personal-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app.jar"]