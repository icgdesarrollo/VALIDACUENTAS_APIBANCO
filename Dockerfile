# First stage: Build the application
FROM maven:3.9-eclipse-temurin-19-alpine as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Second stage: Create Image
FROM eclipse-temurin:19.0.1_10-jre-alpine
COPY --from=build /app/target/validacuentas_apibanco-1.0.0.jar /home/validacuentas_apibanco-1.0.0.jar
CMD ["java","-jar","/home/validacuentas_apibanco-1.0.0.jar"]
