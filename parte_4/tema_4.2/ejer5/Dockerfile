#################################################
# Imagen base para el contenedor de compilación
#################################################
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# Copia las dependencias del proyecto
COPY aplicacion-java-enunciado/pom.xml /project/

# Descarga las dependencias del proyecto
RUN mvn -B clean verify

# Copia el código del proyecto
COPY aplicacion-java-enunciado/src /project/src

# Compila proyecto
RUN mvn -B -o package -DskipTests

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM eclipse-temurin:21-jre

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/src/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/src/app/

# Indica el puerto que expone el contenedor
EXPOSE 8080

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "java-webapp-0.0.1.jar" ]