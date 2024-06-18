#FROM ubuntu:latest
#LABEL authors="USER"
#
#ENTRYPOINT ["top", "-b"]

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-ea-28-jdk-slim-buster
COPY --from=build /target/musik-rec-0.0.1-SNAPSHOT.jar musik-rec.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "musik-rec.jar"]