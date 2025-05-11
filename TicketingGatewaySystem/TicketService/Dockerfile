FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/TicketService.jar TicketService.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "TicketService.jar"]
