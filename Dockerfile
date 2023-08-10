FROM openjdk:17-oracle

WORKDIR /identity
COPY . .
CMD ./mvnw spring-boot:run
EXPOSE 8080