FROM openjdk:8-jdk-alpine

COPY ${PWD}/build/libs /app/libs

EXPOSE 8080

CMD ["java", "-jar", "/app/libs/dev-covid19-api-0.0.1-SNAPSHOT.jar"]
