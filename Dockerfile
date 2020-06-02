FROM openjdk:11.0.7-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} fx-exchange.jar
ENTRYPOINT ["java","-jar","/fx-exchange.jar"]