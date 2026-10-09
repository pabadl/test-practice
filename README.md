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
