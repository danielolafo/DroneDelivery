FROM maven:3.8.5-openjdk-17 AS build
VOLUME /tmp
WORKDIR /app
RUN ls
RUN echo "Next. I'm going to run mvn package"
RUN mvn clean package -DskipTests
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
