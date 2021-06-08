FROM openjdk:11-jre-slim
MAINTAINER "Docker App <docker@app.com>"
WORKDIR /rest-mutant-meli

COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/rest-mutant-meli/app.jar"]

EXPOSE 8080