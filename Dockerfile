# Use a lightweight OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-jammy

ARG JAR_FILE=target/library-api.jar

# Copy the built jar file
COPY ${JAR_FILE} app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
