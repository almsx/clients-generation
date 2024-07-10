# Etapa 1: Construcción
FROM maven:3.8.4-openjdk-17 AS build

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos de configuración de Maven
COPY pom.xml .

# Descargar las dependencias de Maven
#RUN mvn dependency:go-offline

# Copiar el resto del proyecto
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/clients-0.0.1-SNAPSHOT.jar /app/clients.jar

# Hacer el puerto 8080 disponible para el mundo exterior
EXPOSE 8080

# Ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "/app/clients.jar"]

