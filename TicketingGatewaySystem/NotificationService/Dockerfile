FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/NotificationService.jar NotificationService.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "NotificationService.jar"]
