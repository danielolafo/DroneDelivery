FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
RUN ls
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
