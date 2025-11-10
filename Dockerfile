FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
WORKDIR /app

#RUN mvn wrapper:wrapper
#RUN chmod +x mvnw

COPY pom.xml pom.xml

#RUN mvn clean install
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
