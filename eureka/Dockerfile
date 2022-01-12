FROM adoptopenjdk/openjdk11:alpine
COPY ./target/eureka-1.0.0.jar /usr/src/eureka/
WORKDIR /usr/src/eureka
EXPOSE 8888

CMD ["java", "-jar", "eureka-1.0.0.jar"]