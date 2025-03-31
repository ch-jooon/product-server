FROM amazoncorretto:21-alpine as builder

COPY . /app
WORKDIR /app
RUN ./gradlew clean build -x test

FROM amazoncorretto:21-alpine as runner

ARG JAR_FILE=product-api.jar
COPY --from=builder /app/product-api/build/libs/${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
