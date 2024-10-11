# Use Adoptium base image for the build stage
FROM eclipse-temurin:17-jdk as build

# Set the working directory for the build
WORKDIR /ilikaris

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the Maven POM file and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a smaller base image for the final runtime
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /ilikaris

# Copy the built JAR file from the build stage
COPY --from=build /ilikaris/target/JwtAuthenticationF-0.0.1-SNAPSHOT.jar JwtAuthenticationF-0.0.1-SNAPSHOT.jar

# Expose the application port (default Spring Boot port)
EXPOSE 8080

# Command to run the application with external properties
CMD ["java", "-jar", "JwtAuthenticationF-0.0.1-SNAPSHOT.jar", "--spring.config.location=/ilikaris/application.properties"]
