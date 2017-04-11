FROM alpine

RUN apk add --update --no-cache openjdk8-jre

WORKDIR /opt

COPY target/scala-2.12/classes/application.conf .
COPY target/scala-2.12/classes/logback.xml .
COPY target/scala-2.12/content-api-assembly*.jar content-api.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "content-api.jar"]
CMD []