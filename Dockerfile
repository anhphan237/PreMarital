# 🏗️ Stage 1: Build ứng dụng (dev & production)
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Chỉ copy file cần thiết trước để tận dụng cache
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src/ src/

# Cấp quyền thực thi cho mvnw và build project
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# 🌱 Stage 2: Môi trường development (giữ mã nguồn, hỗ trợ hot reload)
FROM eclipse-temurin:17-jdk-alpine AS dev
WORKDIR /app

# Copy toàn bộ mã nguồn để hỗ trợ hot reload
COPY --from=builder /app /app

# Bật hot reload bằng Spring Boot DevTools
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-Dspring.devtools.restart.enabled=true'"]

# 🚀 Stage 3: Môi trường production (chỉ giữ file .jar)
FROM eclipse-temurin:17-jre-alpine AS production
WORKDIR /app

# Chỉ copy file .jar đã build từ builder stage
COPY --from=builder /app/target/premarital-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080 cho ứng dụng
EXPOSE 8080

# Chạy ứng dụng ở chế độ production
ENTRYPOINT ["java", "-jar", "app.jar"]
