FROM openjdk:17-jdk-slim
WORKDIR /wallet
COPY ${JAR_FILE} wallet.jar
ENV JAVA_OPTS=""
EXPOSE 8080
CMD ["sh", "-c", "java $JAVA_OPTS -jar wallet.jar"]