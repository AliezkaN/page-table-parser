FROM openjdk:11
MAINTAINER Oleh Nahorniak <nagornyak68@gmail.com>
EXPOSE 8081
COPY target/page-parser-0.0.1-SNAPSHOT.jar page-parser.jar
ENTRYPOINT ["java","-jar", "/page-parser.jar"]
