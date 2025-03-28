FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x personas-clientes-service/mvnw
RUN personas-clientes-service/mvnw -f personas-clientes-service/pom.xml clean package -DskipTests
EXPOSE 8081
CMD ["java", "-jar", "personas-clientes-service/target/personas-clientes-service-0.0.1-SNAPSHOT.jar"]