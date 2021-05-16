FROM openjdk:8-jdk-alpine
COPY /cinema.jar cinemaapp.jar
ENTRYPOINT ["java","-jar", "-Daxoniq.axonserver.hostname=axonserver","/cinemaapp.jar"]
