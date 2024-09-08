# Etapa 1: Compilar la aplicación
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
COPY src ./src

# Compilar la aplicación Spring Boot
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el archivo .jar construido desde la etapa anterior
COPY --from=build /app/target/productos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del API Gateway
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
