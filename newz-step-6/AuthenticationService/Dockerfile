FROM adoptopenjdk/openjdk11:latest
WORKDIR usr/src
ADD ./target/AuthenticationService-0.0.1-SNAPSHOT.jar /usr/src/AuthenticationService-0.0.1-SNAPSHOT.jar
ENV MYSQL_ROOT_PASSWORD=password
ENTRYPOINT ["java","-jar","AuthenticationService-0.0.1-SNAPSHOT.jar"]