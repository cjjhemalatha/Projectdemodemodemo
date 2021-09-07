FROM java:8-jdk-alpine
COPY /target/project03-1-0.0.1-SNAPSHOT.jar /pass-app.jar
CMD ["java","-jar","pass-app.jar"]