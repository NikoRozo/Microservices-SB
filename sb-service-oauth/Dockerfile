FROM openjdk:11
VOLUME /tmp
EXPOSE 9100
ADD ./target/sb-service-oauth-0.0.1-SNAPSHOT.jar service-oauth.jar
ENTRYPOINT ["java", "-jar", "service-oauth.jar"]