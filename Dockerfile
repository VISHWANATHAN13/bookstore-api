# Use lightweight Java JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Copy your Spring Boot jar into the container
COPY target/bookstore-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Command to run your app
ENTRYPOINT ["java","-jar","/app.jar"]
