# Imagen base con Maven y Java 11
FROM maven:3.9.6-eclipse-temurin-11

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el proyecto completo
COPY . .

# Descargamos dependencias primero (mejora performance)
RUN mvn -B -q dependency:resolve

# Comando por defecto: ejecutar pruebas
CMD ["mvn", "verify"]
