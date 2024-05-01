# The base image. 
FROM eclipse-temurin:21-jdk-alpine

# The work directory. 
WORKDIR /

# The environment port to expose. 
ENV PORT=9090
EXPOSE 9090

# The JAR file path. 
# ARG JAR_FILE=*.jar
# ARG JAR_FILE=the-review-room-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build context into the Docker image. 
# COPY target/${JAR_FILE} application.jar

# Copy the source code into the Docker image.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src

# Modify the permissions of the mvnw script. 
RUN chmod +x ./mvnw

# CMD apk update \
#     && rm -rf /var/cache/apk/*

# Set the default command to run the Java application. 
#ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
# ENTRYPOINT ["java","-jar","/application.jar"]
RUN ./mvnw install -DskipTests
CMD ["./mvnw", "spring-boot:run"]
