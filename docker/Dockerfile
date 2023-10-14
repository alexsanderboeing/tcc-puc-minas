FROM openjdk:11-jre-slim

ENV TZ America/Sao_Paulo

COPY ./target/junta-medica.jar /app/

ENTRYPOINT exec java -Duser.country=BR -Duser.language=pt -jar /app/junta-medica.jar

EXPOSE 9016
