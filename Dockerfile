

FROM openjdk:17-oracle
COPY . .

RUN ./mvnw clean package

# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/identity-0.0.1-SNAPSHOT.jar"]