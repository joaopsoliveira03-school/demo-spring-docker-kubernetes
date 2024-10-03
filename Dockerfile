FROM amazoncorretto:17-alpine
WORKDIR /app
COPY . /app
VOLUME /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "chmod +x ./mvnw && ./mvnw spring-boot:run"]