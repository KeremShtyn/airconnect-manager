FROM openjdk:11

LABEL com.chainerist="airport-manager"

COPY  target/app.jar /app

