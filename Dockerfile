FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/transaction-generator.jar app.jar
CMD "java -Dspring.profiles.active=prod -Dserver.port=$PORT -jar /app.jar"