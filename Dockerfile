# 1. Maven ilə build üçün imic
FROM maven:3.9.6-eclipse-temurin-17 AS build

# 2. Layihə fayllarını konteynerə kopyala
COPY . /app
WORKDIR /app

# 3. Layihəni build et
RUN mvn clean package -DskipTests

# 4. Minimal Java imici ilə son imici yarat
FROM eclipse-temurin:17-jdk
COPY --from=build /app/target/*.jar app.jar

# 5. Portu aç
EXPOSE 8080

# 6. Tətbiqi işə sal
ENTRYPOINT ["java", "-jar", "app.jar"]
