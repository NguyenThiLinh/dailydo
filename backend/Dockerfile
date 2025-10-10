# backend/Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy file cấu hình Gradle
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Cài đặt dependencies (cache step)
RUN ./gradlew build -x test --continue || true

# Copy toàn bộ source code
COPY . .

# Port BE
EXPOSE 9090

# Chạy app bằng Gradle để hot reload
CMD ["./gradlew", "bootRun"]