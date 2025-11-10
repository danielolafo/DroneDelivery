FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
RUN mvn clean install
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
