FROM openjdk:11
MAINTAINER Oleh Nahorniak <nagornyak68@gmail.com>
EXPOSE 8081
COPY target/*.jar page-parser.jar
ENTRYPOINT ["java","-jar", "/page-parser.jar"]
