FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache maven # For Alpine Linux
#WORKDIR /app


RUN mvn clean package -DskipTests
COPY /target/*.jar app.jar
RUN chmod +x app.jar
ENTRYPOINT ["java","-jar","app.jar"]