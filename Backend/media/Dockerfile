#FROM ubuntu:latest
#LABEL authors="devar"
#
#ENTRYPOINT ["top", "-b"]

# -------- BUILD STAGE --------
FROM maven:3.9.12-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# -------- RUN STAGE --------
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
