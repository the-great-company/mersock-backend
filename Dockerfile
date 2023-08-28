# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project descriptor files
COPY pom.xml .

# Copy the application source code
COPY src src

# Build the application
# RUN mvn dependency:resolve
RUN mvn install -DskipTests

# Stage 2: Run the application
#FROM openjdk:17-slim
FROM bellsoft/liberica-runtime-container:jre-17-stream-musl

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/spring-webapp-0.0.1.jar app.jar

# Expose the port that the application listens on
EXPOSE 8080

# Set the entry point command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
