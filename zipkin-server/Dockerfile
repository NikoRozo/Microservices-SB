FROM openjdk:11
VOLUME /tmp
EXPOSE 9411
ADD ./zipkin-server-2.12.9-exec.jar zipkin-server.jar
ENTRYPOINT ["java","-jar","/zipkin-server.jar"]