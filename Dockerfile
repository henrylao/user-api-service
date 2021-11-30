FROM adoptopenjdk/openjdk16:ubi
RUN mkdir /opt/app
COPY japp.jar /opt/app
EXPOSE 8080
#ARG JAR_FILE=target/my-application.jar
#ADD ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]
