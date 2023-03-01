FROM openjdk:17-jdk-slim
VOLUME /tmp

RUN groupadd spring && useradd spring -g spring
USER spring:spring
COPY ec-ms-rates-boot/target/ec-ms-rates-boot*.jar app.jar

ENTRYPOINT ["java","-jar", "/app.jar"]