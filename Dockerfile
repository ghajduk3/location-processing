FROM openjdk:9-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/location-processing-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8083

CMD java -jar location-processing-api-1.0.0-SNAPSHOT.jar