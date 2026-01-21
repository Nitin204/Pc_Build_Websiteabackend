# Use Maven + Java 17 for build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (cached)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source code
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Use lightweight JDK runtime for running
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8181

# Run the app
CMD ["java", "-jar", "app.jar"]
