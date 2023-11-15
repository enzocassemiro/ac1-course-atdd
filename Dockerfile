FROM gradle:7.4.0-jdk17 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:17-jdk

COPY --from=builder /home/gradle/src/build/libs/Cursos-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8070

ENTRYPOINT ["java","-jar","/app/Cursos-0.0.1-SNAPSHOT.jar"]
