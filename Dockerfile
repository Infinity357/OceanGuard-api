# Build stage
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle gradle

# Make gradlew executable
RUN chmod +x gradlew

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src src

# Build application
RUN ./gradlew build -x test --no-daemon

# ================================
# Runtime stage
# ================================
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the jar built in the previous stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
