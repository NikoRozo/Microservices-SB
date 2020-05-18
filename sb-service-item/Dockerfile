FROM openjdk:11
VOLUME /tmp
ADD ./target/sb-service-item-0.0.1-SNAPSHOT.jar service-items.jar
ENTRYPOINT ["java", "-jar", "service-items.jar"]