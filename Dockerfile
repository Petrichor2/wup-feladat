FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD

COPY ./ ./

RUN mvn -f library-common/pom.xml install
RUN mvn -f library-impl/pom.xml clean package

FROM openjdk:8-jre-alpine3.9

COPY --from=MAVEN_BUILD /library-impl/target/library-impl-1.0.0-SNAPSHOT.jar /application.jar

CMD ["java", "-jar", "/application.jar"]
