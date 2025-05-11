FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/GatewayService.jar GatewayService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GatewayService.jar"]
