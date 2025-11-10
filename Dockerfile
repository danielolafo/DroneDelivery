FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache maven # For Alpine Linux
#VOLUME /tmp
WORKDIR /app

#RUN mvn wrapper:wrapper
#RUN chmod +x mvnw

COPY . .

RUN mvn clean package -DskipTests
#COPY --from=build target/*.jar /app/app.jar
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8080