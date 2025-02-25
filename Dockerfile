# Sử dụng OpenJDK 17 (có thể đổi thành OpenJDK 11 nếu cần)
FROM openjdk:17-jdk-slim 

# Đặt thư mục làm việc trong container
WORKDIR /app 

# Copy file JAR vào container
COPY target/premarital-0.0.1-SNAPSHOT.jar app.jar

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]