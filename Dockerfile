FROM openjdk:17-oracle

WORKDIR /identity
COPY . .
RUN ./mvnw clean install
CMD ./mvnw spring-boot:run
EXPOSE 8080