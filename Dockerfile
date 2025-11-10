FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY pom.xml .
COPY src src

# Copy Maven wrapper
COPY mvnw .
COPY .mvn .mvn

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
RUN mvn clean install

# Stage 2: Create the final Docker image using OpenJDK 19
FROM openjdk:19-jdk
VOLUME /tmp

COPY target/*.jar DroneDelivery-1.0.jar

ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080 