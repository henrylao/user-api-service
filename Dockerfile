#FROM openjdk:16-jdk-oraclelinux7
# TODO: update to use JDK 11
MAINTAINER hcl.henry.lao
EXPOSE 8080
# TODO: update to connect to MySQL
ARG JAR_FILE=target/my-application.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
