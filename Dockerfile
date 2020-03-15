FROM openjdk:jdk-alpine
VOLUME /tmp
WORKDIR /app
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build/libs/demo-0.0.1-SNAPSHOT.jar" ]