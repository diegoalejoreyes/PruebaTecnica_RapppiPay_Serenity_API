# Prueba Técnica QA Automation – API REST (Serenity BDD)

## 1. Descripción General

Este proyecto implementa pruebas automatizadas sobre una **API REST pública (Shazam API)**, específicamente en los endpoints **GET `/songs/v2/get-details`** y **POST `/songs/detect`**, utilizando **Serenity BDD**, **Cucumber**, **Java 11** y **Maven**.

La solución cumple con los requerimientos funcionales, técnicos y el **bonus** del ejercicio, implementando **Docker** para garantizar la portabilidad de la ejecución.

---

## 2. Stack Tecnológico

* **Lenguaje:** Java 11
* **Gestión de dependencias:** Maven
* **Framework BDD:** Serenity BDD
* **BDD:** Cucumber
* **Testing REST:** Serenity REST
* **Contenedor:** Docker
---

## 3. Arquitectura y Decisiones de Diseño

El proyecto sigue una **arquitectura en capas**, alineada con buenas prácticas de automatización y mantenibilidad:

```
src
 └── test
     ├── java
     │   ├── runners
     │   │   └── CucumberTestSuite.java
     │   ├── stepdefinitions
     │   │   └── SongStepDefinitions.java
     │   ├── tasks
     │   │   ├── GetSongs.java
     │   │   └── PostSongs.java
     │   ├── utils
     │   │   ├── CargaPayload.java
     │   └── questions
     │       └── LastResponseStatusCode.java
     └── resources
         ├── data
         │   └── clinteastwood_portion_mono.txt
         ├── features
         │   └── songsApi.feature
         └── serenity.conf

```

### Decisiones Clave

* **Uso de Serenity REST:**
  Se utiliza exclusivamente la capa REST de Serenity para cumplir el requerimiento explícito del ejercicio.

* **Payload externo (`.txt`):**
  El cuerpo del POST se lee desde un archivo de texto plano, configurable desde `serenity.conf`, manteniendo los escenarios BDD limpios y desacoplados de datos técnicos.

* **Configuración centralizada:**
  La `base.url`, `apiKey`, `host` y ruta del payload se gestionan desde `serenity.conf` mediante el bloque `environments`, simplificando la ejecución en distintos entornos.

* **Docker:**
  Se incorpora Docker para asegurar la ejecución consistente del proyecto en cualquier máquina sin dependencias locales.

---

## 4. Configuración del Proyecto

### Archivo `serenity.conf`

La configuración principal del proyecto se encuentra en `serenity.conf`:

```hocon
environments {
  default {
    base.url = "https://shazam.p.rapidapi.com"
    apiKey = "9881adffaamsha73c5d044918fdep16b143jsn964a48efb448"
    host = "shazam.p.rapidapi.com"
    payload= "src/test/resources/data/clinteastwood_portion_mono.txt"
  }
}
```

> Para este caso, la API Key se mantiene fija en `serenity.conf` para facilitar la ejecución y evaluación. En un entorno productivo, lo ideal es manejarlo con variables de entorno.

---

## 5. Ejecución Local (sin Docker)

### Prerrequisitos

* Java 11 instalado
* Maven instalado

### Pasos

1. Clonar el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
cd Prueba_API_Shazam_RappiPay
```

2. Ejecutar las pruebas:

```bash
mvn clean verify
```

3. Visualizar el reporte Serenity:

```
target/site/serenity/index.html
```

---

## 6. Ejecución con Docker (Adicional)

### Dockerfile utilizado

```dockerfile
FROM maven:3.9.6-eclipse-temurin-11

WORKDIR /app

COPY . .

RUN mvn -B -q dependency:resolve

CMD ["mvn", "verify"]
```

### Pasos de ejecución (Windows – PowerShell)

1. Construir la imagen:

```powershell
docker build -t serenity-api-tests .
```

2. Ejecutar el contenedor:

```powershell
docker run --rm -v ${PWD}\target:/app/target serenity-api-tests
```

> Se ejecuta `mvn verify` para evitar conflictos de permisos en Windows al montar el directorio `target` como volumen.

3. Visualizar el reporte:

```
target/site/serenity/index.html
```

---

## 9. Notas Finales

* El proyecto cumple con los requerimientos funcionales y técnicos del ejercicio propuesto.
* Se implemnto Docker para garantizar portabilidad y reproducibilidad.
* Se maneja Serenity BDD que facilita reportes claros y trazabilidad de la ejecución.

---

**Autor:** Diego Reyes
