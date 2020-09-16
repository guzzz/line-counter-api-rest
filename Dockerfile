FROM openjdk:11
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/line-counter-api-rest.jar
WORKDIR /app
ENTRYPOINT java -jar line-counter-api-rest.jar