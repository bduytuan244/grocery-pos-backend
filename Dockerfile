# Nhịp 1: Mượn máy ảo có Maven và Java 21 để đóng gói code
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Nhịp 2: Đem file .jar đó qua một máy ảo Java 21 siêu nhẹ để chạy 24/7
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]