FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/sb-service-eureka-server-0.0.1-SNAPSHOT.jar config-eureka.jar
ENTRYPOINT ["java", "-jar", "config-eureka.jar"]