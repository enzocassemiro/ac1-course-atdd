FROM gradle:7.3.0-jdk17 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:17-jdk-alpine

COPY --from=builder /home/gradle/src/build/libs/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]