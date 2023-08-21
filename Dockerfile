

FROM openjdk:17-oracle

RUN ./mvnw clean package

COPY --from=build /target/identity-0.0.1-SNAPSHOT.jar identity.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","identity.jar"]