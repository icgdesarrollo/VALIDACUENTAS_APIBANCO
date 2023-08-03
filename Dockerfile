FROM eclipse-temurin:19.0.1_10-jre-alpine
COPY ./target/validacuentas_apibanco-1.0.0.jar /home/validacuentas_apibanco-1.0.0.jar
CMD ["java","-jar","/home/validacuentas_apibanco-1.0.0.jar"]
