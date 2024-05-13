FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /opt/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean verify -DskipTests

FROM node:18-alpine as frontend
WORKDIR /frontend
COPY src /frontend/src 
COPY /frontend/package.json /frontend/package-lock.json /frontend/ 
RUN npm install
CMD ["npm", "run","dev", "--prefix", "/opt/app/frontend"]

FROM eclipse-temurin:21-jdk-jammy

RUN apt-get update && \
    apt-get install -y nodejs=17.9.1 npm && \
    rm -rf /var/lib/apt/lists/*
RUN apt-get update && apt-get install -y postgresql && rm -rf /var/lib/apt/lists/*

USER postgres

RUN service postgresql start && \
    psql -c "CREATE DATABASE the_review_room;" && \
    sed -i '/# TYPE  DATABASE        USER            ADDRESS                 METHOD/,$ s/scram-sha-256/trust/g' $(psql -U postgres -t -P format=unaligned -c 'show hba_file') && \
    service postgresql restart

WORKDIR /opt/app

EXPOSE 5432
EXPOSE 9090

# The ENV PORT is the port that the main app will be served. In this case we will be using frontend vite port
ENV PORT=5173
EXPOSE $PORT

COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
COPY --from=frontend /frontend /opt/app/frontend

ENTRYPOINT npm run dev --prefix /opt/app/frontend && service postgresql start && while ! pg_isready -h localhost -p 5432; do sleep 1; done && java -jar /opt/app/*.jar 

# # Start frontend server
# CMD ["npm", "run","dev", "--prefix", "/opt/app/frontend"]