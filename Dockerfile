FROM heroku/heroku-buildpack-gradle
VOLUME /tmp
ADD build/libs/transaction-generator.jar app.jar
CMD java -jar app.jar -Dserver.port=$PORT
expose $PORT