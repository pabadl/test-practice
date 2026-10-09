# Multi-stage Dockerfile: build with Maven, run with a minimal JRE image
# Multi-stage: primera etapa compila la aplicación, segunda ejecuta solo el runtime.
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copiar primero los archivos de Maven para aprovechar el cache de capas
# Esto permite que Docker reutilice las dependencias entre builds si `pom.xml` no cambia.
COPY pom.xml ./
COPY src ./src

# Compilar la aplicación
# Nota: actualmente se omiten los tests con `-DskipTests` para acelerar la construcción.
# Si quieres que la imagen falle cuando los tests fallan, elimina `-DskipTests`.
RUN mvn -B package
# Compilar la aplicación omitiendo los tests
#RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar el JAR empaquetado desde la etapa de build
# Spring Boot con el plugin Maven genera el ejecutable en `target/*.jar`.
COPY --from=build /workspace/target/*.jar app.jar

# Documenta el puerto en el que la aplicación escucha (no abre el puerto por sí solo)
# Mapea el puerto al ejecutar: `docker run -p 8080:8080 ...`
EXPOSE 8080

# Comando de inicio del contenedor: ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
