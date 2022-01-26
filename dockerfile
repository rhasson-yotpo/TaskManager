FROM adoptopenjdk/openjdk11

ARG JARFILE

COPY ${JARFILE} app.jar 
ADD sleep.sh .
RUN ["chmod", "u+x", "sleep.sh"]

EXPOSE 8080

RUN ./sleep.sh

ENTRYPOINT [ "java", "-jar", "/app.jar" ]




