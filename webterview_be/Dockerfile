FROM openjdk:8
COPY target/webterview_be-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
VOLUME ["/var/log"]
EXPOSE 3000
