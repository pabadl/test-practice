# test-practice

Instrucciones rápidas para Docker

Construir la imagen:

```bash
docker build -t test-practice:latest .
```

Ejecutar la app (mapea el puerto 8080):

```bash
docker run --rm -p 8080:8080 test-practice:latest
```

Modo detached (ejecutar en segundo plano):

```bash
docker run -d -p 8080:8080 --name test-practice test-practice:latest
```

Ver logs en tiempo real:

```bash
docker logs -f test-practice
```

Detener el contenedor:

```bash
docker stop test-practice
```

Eliminar el contenedor (si quieres liberar el nombre):

```bash
docker rm test-practice
```

Tests y cobertura:

- Ejecutar todos los tests unitarios:

```bash
mvn test
```

- Ejecutar una clase de test concreta (ej. ProductControllerTest):

```bash
mvn -Dtest=ProductControllerTest test
```

- Ejecutar tests y generar reporte de cobertura (JaCoCo) — output en `target/site/jacoco/index.html`:

```bash
mvn verify
# o alternativamente
mvn test jacoco:report
```

- Ejecutar tests y cobertura dentro de un contenedor Maven (si prefieres Docker):

```bash
docker run --rm -v "$PWD":/workspace -w /workspace maven:3.9.4-eclipse-temurin-17 mvn -B verify
```
