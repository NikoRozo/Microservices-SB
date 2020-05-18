FROM openjdk:11
VOLUME /tmp
ADD ./target/sb-service-product-0.0.1-SNAPSHOT.jar service-products.jar
ENTRYPOINT ["java", "-jar", "service-products.jar"]