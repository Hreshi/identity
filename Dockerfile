

FROM openjdk:17-oracle
COPY . .

# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","identity.jar"]
