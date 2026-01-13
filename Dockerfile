# Build stage
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy wrapper scripts and folder
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Copy project files
COPY pom.xml .
COPY src ./src

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build jar
RUN ./mvnw clean package

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/bookstore-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
