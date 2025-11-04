# ----------------------------------------------------------------------------------
# STAGE 1: BUILD THE JAR FILE
# ----------------------------------------------------------------------------------
# Use a robust image with the full Java SDK and Gradle installed for building
FROM gradle:8.9-jdk21-jammy AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper scripts (required to run Gradle inside Docker)
COPY gradlew .
COPY gradle/wrapper/ /app/gradle/wrapper/

# Copy the build configuration files first to allow caching of dependencies
COPY build.gradle settings.gradle /app/

# Fetch all dependencies (if the build files haven't changed, this step is cached)
RUN ./gradlew dependencies

# Copy the source code
COPY src /app/src

# Build the Spring Boot application, creating the final JAR
RUN ./gradlew bootJar

# ----------------------------------------------------------------------------------
# STAGE 2: CREATE THE FINAL, SMALLER RUNTIME IMAGE
# ----------------------------------------------------------------------------------
# Use a lean, production-ready JRE image (e.g., Eclipse Temurin for Java 21)
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Expose the port your Spring Boot app uses (default 8080)
EXPOSE 8080

# Copy the final executable JAR file from the 'builder' stage
# The name 'app.jar' is the standard output name for Spring Boot JARs
COPY --from=builder /app/build/libs/*.jar app.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]