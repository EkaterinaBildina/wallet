FROM maven:3.9.9 AS builder
WORKDIR /JavaPJT/Wallet/wallet/wallet
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:11-jre-slim
WORKDIR /JavaPJT/Wallet/wallet/wallet
COPY --from=builder /wallet/target/wallet-app.jar wallet.jar
ENTRYPOINT ["java", "-jar", "wallet.jar"]
EXPOSE 8080