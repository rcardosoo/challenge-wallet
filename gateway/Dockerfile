FROM adoptopenjdk/openjdk11:alpine
COPY ./target/gateway-1.0.0.jar /usr/src/gateway/
WORKDIR /usr/src/gateway
EXPOSE 8891

CMD ["java", "-jar", "gateway-1.0.0.jar"]