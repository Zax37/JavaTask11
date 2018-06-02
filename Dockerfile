FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/transaction-generator.jar app.jar
CMD "java -jar /app.jar"