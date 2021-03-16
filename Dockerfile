FROM openjdk:11.0.10-slim
WORKDIR /keycloak
COPY target/*.jar keycloak-demo.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/keycloak/keycloak-demo.jar"]


