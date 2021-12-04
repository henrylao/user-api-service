#FROM openjdk:16-jdk-oraclelinux7
MAINTAINER hcl.henry.lao
EXPOSE 8080
ARG JAR_FILE=target/my-application.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]