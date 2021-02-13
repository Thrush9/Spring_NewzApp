FROM adoptopenjdk/openjdk11:latest
WORKDIR usr/src
ADD ./target/NewsSourceService-0.0.1-SNAPSHOT.jar /usr/src/NewsSourceService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","NewsSourceService-0.0.1-SNAPSHOT.jar"]