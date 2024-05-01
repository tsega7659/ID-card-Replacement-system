FROM maven:3.8.2-openjdk-17-slim AS build


COPY pom.xml /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml dependency:go-offline

COPY src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml package

FROM openjdk:17-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /usr/src/app/${JAR_FILE} /usr/src/app/app.jar

ENTRYPOINT ["java","-jar","/usr/src/app/app.jar"]