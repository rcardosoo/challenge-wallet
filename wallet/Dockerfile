FROM adoptopenjdk/openjdk11:alpine
COPY ./target/wallet-1.0.0.jar /usr/src/wallet/
WORKDIR /usr/src/wallet
EXPOSE 8890

CMD ["java", "-jar", "wallet-1.0.0.jar"]