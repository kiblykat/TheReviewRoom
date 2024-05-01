# The base image to build. 
FROM eclipse-temurin:21-jdk-jammy as builder
# FROM eclipse-temurin:21-jdk-alpine as builder

# The build work directory. 
WORKDIR /opt/app

# Copy the source code into the Docker image.
# Docker uses caching to speed up builds by reusing layers from previous builds. 
# To take advantage of caching, you should order your Dockerfile instructions so that the ones that change frequently are placed towards the end of the file. 
# For example, if you’re copying files into the image, you should do that at the end of the Dockerfile.
# The JAR file path. 
# ARG JAR_FILE=*.jar
# ARG JAR_FILE=the-review-room-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build context into the Docker image. 
# COPY target/${JAR_FILE} application.jar

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Modify the permissions of the mvnw script. 
RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

# RUN apk add --no-cache \
#         package1 \
#         package2 \
#         package3 && \
#     rm -rf /var/cache/apk/* /tmp/*

# RUN apt-get update && \
#     rm -rf /var/lib/apt/lists/*

# Compile the Java application.
RUN ./mvnw clean install -DskipTests

# The base image to package. 
FROM eclipse-temurin:21-jdk-jammy
# FROM eclipse-temurin:21-jdk-alpine

# Setup a non-root user with user privileges instead of root privileges. 
# RUN adduser -D myuser
# RUN addgroup usergroup; adduser --ingroup usergroup --disabled-password myuser; 

RUN apt-get update && apt-get install -y postgresql postgresql-contrib gosu

# RUN service postgresql start && gosu postgres psql -c "ALTER DATABASE postgres RENAME TO the_review_room;"
RUN service postgresql start && gosu postgres psql -c "CREATE DATABASE the_review_room;"

# The USER Dockerfile instruction sets the preferred user name (or UID) and optionally the user group (or GID) while running the image — and for any subsequent RUN, CMD, or ENTRYPOINT instructions. 
# USER myuser

# The deployment work directory. 
WORKDIR /opt/app

# The environment port to expose. 
ENV PORT=9090
EXPOSE $PORT

COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar

# Create a script that starts the PostgreSQL service and waits until it's ready. 
# gosu postgres psql -c \"CREATE DATABASE the_review_room;\"\n\
# RUN echo "#!/bin/bash\n\
#     service postgresql start\n\
#     while ! pg_isready -h localhost -p 5432 > /dev/null 2> /dev/null; do\n\
#     echo \"Waiting for database to start... \"\n\
#     sleep 2\n\
#     done\n\
#     java -jar /opt/app/*.jar" > /start.sh && chmod +x /start.sh && chmod 777 /var/run/postgresql

# Set the default command to run the Java application. 
# The ENTRYPOINT instruction specifies the command that should be run.
# ENTRYPOINT ["java"]
# ENTRYPOINT [ "./mvnw" ]
# ENTRYPOINT service postgresql start && sleep 10 && gosu postgres psql -c "CREATE DATABASE the_review_room;" && java -jar /opt/app/*.jar
ENTRYPOINT service postgresql start && sleep 10 && java -jar /opt/app/*.jar
# ENTRYPOINT ["/bin/bash"]

# The CMD instruction provides default arguments to the ENTRYPOINT command.
# CMD ["-Xmx2048M", "-jar", "/application.jar"] # Set a Java heap size of 2GB for the run. 
# CMD ["-jar","/application.jar"]
# CMD ["./mvnw", "spring-boot:run"]
# CMD ["spring-boot:run"]
# CMD ["-jar","/opt/app/*.jar"]
# CMD ["/start.sh"]