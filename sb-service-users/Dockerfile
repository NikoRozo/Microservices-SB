FROM openjdk:11
VOLUME /tmp
ADD ./target/sb-service-users-0.0.1-SNAPSHOT.jar service-users.jar
ENTRYPOINT ["java", "-jar", "service-users.jar"]