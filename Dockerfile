FROM openjdk:8-alpine
RUN mkdir /opt/services/
WORKDIR /opt/services
EXPOSE 8080
COPY target/spring-boot-kubernetes.jar app.jar
CMD ["java", "-jar", "app.jar"]
