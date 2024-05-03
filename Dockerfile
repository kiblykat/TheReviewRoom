# The base image to build. Define the context name for the build stage. 
FROM eclipse-temurin:21-jdk-alpine as builder

# The build work directory. 
WORKDIR /opt/app

# Copy the source code into the Docker image to be used for compiling. 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Modify the permissions of the mvnw script to be executable. 
RUN chmod +x ./mvnw

# This command downloads all the dependencies that are required for the project. 
RUN ./mvnw dependency:go-offline

# Copy the source code into the Docker image to be used for compiling. 
COPY src ./src

# Compile the Java application, then generate the deployment jar file. 
RUN ./mvnw clean install -DskipTests

# The base image to package. This is a multi-stage build using a new context. 
FROM eclipse-temurin:21-jdk-jammy

# Install PostgreSQL and change PostgreSQL authentication to trust. 
# Install the PostgreSQL package. Remove the package lists to reduce the image size. 
RUN apt-get update && apt-get install -y postgresql && && rm -rf /var/lib/apt/lists/*

# As a security best practice, switch to a non-root user with user privileges instead of root privileges. 
# The USER Dockerfile instruction sets the preferred user name (or UID) and optionally the user group (or GID) while running the image — and for any subsequent RUN, CMD, or ENTRYPOINT instructions. 
# Change the user to the PostgreSQL user. 
USER postgres

# Start the PostgreSQL service and create a new database used by the application, then change the PostgreSQL authentication method to trust and restart the PostgreSQL service. 
RUN service postgresql start && \
    psql -c "CREATE DATABASE the_review_room;" && \
    sed -i '/# TYPE  DATABASE        USER            ADDRESS                 METHOD/,$ s/scram-sha-256/trust/g' $(psql -U postgres -t -P format=unaligned -c 'show hba_file') && \
    service postgresql restart

# The deployment work directory. 
WORKDIR /opt/app

# The environment port(s) to expose. The EXPOSE instruction informs Docker that the container listens on the specified network ports at runtime. 
# Expose the PostgreSQL port. This is not actually accessible from outside the container when deployed on Render, but it is useful for linking containers together or directly connecting to the database from the host machine. 
EXPOSE 5432

# Expose the application port. This is the port that the application listens on for incoming requests. 
ENV PORT=9090
EXPOSE $PORT

# Copy the Jar file generated from the builder context into the Docker image. 
# Docker uses caching to speed up builds by reusing layers from previous builds. 
# To take advantage of caching, you should order your Dockerfile instructions so that the ones that change frequently are placed towards the end of the file. 
# For example, if you’re copying files into the image, you should do that at the end of the Dockerfile. 
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar

# Set the default command to run the Java application. 
# The ENTRYPOINT instruction specifies the command that should be run. The CMD instruction provides default arguments to the ENTRYPOINT command. 
# Start the PostgreSQL service, then wait for it to start by checking with pg_isready (a utility specifically designed for checking if PostgreSQL is ready to accept connections). Once started, run the Java application via its Jar file. 
ENTRYPOINT service postgresql start && while ! pg_isready -h localhost -p 5432; do sleep 1; done && java -jar /opt/app/*.jar