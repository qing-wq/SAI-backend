FROM openjdk:8-jdk-alpine
VOLUME /home/qing/IdeaProjects/sai-backend
ADD target/sai-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]