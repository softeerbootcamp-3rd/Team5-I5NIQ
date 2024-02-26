FROM openjdk:17
EXPOSE 8080
COPY out/artifacts/traffic_controller_jar/traffic-controller.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
