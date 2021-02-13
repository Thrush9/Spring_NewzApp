FROM adoptopenjdk/openjdk11:latest
WORKDIR usr/src
ADD ./target/UserProfileService-0.0.1-SNAPSHOT.jar /usr/src/UserProfileService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserProfileService-0.0.1-SNAPSHOT.jar"]