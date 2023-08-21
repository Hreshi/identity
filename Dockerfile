FROM openjdk:17-oracle

WORKDIR /identity
COPY . .
EXPOSE 8080
CMD ./mvnw spring-boot:run