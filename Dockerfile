# the base image
FROM amazoncorretto:17

# the JAR file path
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context into the Docker image
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-jar", "/app.jar"]
