# Stage 1: Build the application
FROM maven:latest AS build
COPY . .
RUN mvn clean install

# Stage 2: Run the application
FROM openjdk:21-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
