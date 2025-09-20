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
