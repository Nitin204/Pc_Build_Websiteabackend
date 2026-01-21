# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy your Spring Boot jar
COPY target/app.jar app.jar

# Expose port (Render will use ${PORT})
EXPOSE 8181

# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]
