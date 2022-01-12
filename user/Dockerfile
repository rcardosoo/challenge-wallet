FROM adoptopenjdk/openjdk11:alpine
COPY ./target/user-1.0.0.jar /usr/src/user/
WORKDIR /usr/src/user
EXPOSE 8889

CMD ["java", "-jar", "user-1.0.0.jar"]
