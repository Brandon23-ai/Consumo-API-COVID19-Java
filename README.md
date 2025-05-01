# Proyecto API COVID-19 - Consumo, Almacenamiento y Multihilo

Este proyecto desarrollado en **Java** realiza el consumo de una API pública de estadísticas de COVID-19, parsea la información y la guarda en una **base de datos** utilizando **JPA (Jakarta Persistence API)**. Además, utiliza **multithreading** y **Log4j2** para mejorar la gestión de procesos y el registro de logs.

---
## Arquitectura del Proyecto

Está construido con un enfoque minimalista y educativo, utilizando **JPA puro sin ningún framework adicional como Spring o Spring Boot**. Toda la configuración, conexión a la base de datos y lógica de negocio está implementada manualmente para tener un mayor control sobre el ciclo de vida de las entidades, la persistencia y el consumo de servicios externos.

El objetivo es entender y dominar los fundamentos de:
- JPA e Hibernate sin dependencias de alto nivel.
- Gestión manual del `EntityManager`.
- Diseño limpio siguiendo el principio de responsabilidad única (SRP).
- Control total sobre los hilos y el flujo de ejecución.

##  Tecnologías utilizadas

- Java 17  
- JPA (Jakarta Persistence API)  
- Hibernate  
- MySQL   
- Log4j2  
- GSON (Google para parseo JSON)  
- Threads en Java  
- Maven (gestor de dependencias)  

---

##  Estructura del Proyecto

- `models/` → Entidades JPA para la base de datos (`Reports`, `Region`, `RequestedData`, etc.).
- `dtos/` → Objetos de transferencia de datos para parsear la API.
- `services/` → Lógica de negocio para consumir la API, guardar datos, verificar duplicados, y consultar reportes.
- `threads/` → Hilos para consumo automático después de un delay.
- `META-INF/persistence.xml` → Configuración de conexión a la base de datos.
- `application.properties` → Configuración del tiempo de espera, ISO del país y fecha de reporte a consultar.
- `log4j2.xml` → Configuración de Log4j2 para manejo de logs.

---

##  ¿Cómo funciona?

1. **Al iniciar el proyecto**, un hilo (`ApiDataLoader`) espera los segundos configurados en `application.properties`.
2. Luego de esperar, **consume** en orden:
   - **Regiones** (`regions` endpoint)
   - **Provincias** (`provinces` endpoint)
   - **Reportes** (`reports` endpoint)
3. **Verifica si ya se consultó** previamente una combinación ISO + fecha usando la nueva tabla `RequestedData` para evitar duplicados.
4. **Guarda** la información en tu base de datos.
5. **Genera logs** en consola y en archivo `.txt` ubicado en la carpeta `/logs`.

---

##  Mejoras implementadas

-  **Evita ejecuciones repetidas** por país y fecha.  
  Se implementó una clase `RequestedDataService` y una entidad `RequestedData` que registra cada consulta hecha con ISO y fecha, junto a la fecha real del sistema. Esto evita múltiples registros duplicados al volver a ejecutar la app.

-  **Consulta de reportes ordenados y sin duplicados.**  
  Se añadio un bloque de metodos usando SRP en `ReportsService` que permite consultar reportes almacenados para un país y fecha específicos, usando un `TreeMap<String, Reports>` para eliminar duplicados y ordenar alfabéticamente por provincia.

-  **Refactorización de métodos (mejora de código).**  
  Los métodos fueron reorganizados aplicando el principio de **Single Responsibility**, logrando una estructura modular, limpia y fácil de mantener.

-  **Logs persistentes.**  
  Todos los logs generados durante la ejecución también se almacenan en un archivo `.txt` dentro de la carpeta `/logs`, lo cual facilita la trazabilidad de errores y auditoría.

---

##  Configuración inicial

1. Asegúrate de tener el archivo `application.properties`:
    ```properties
    app.api.delay=15000
    app.api.iso=GTM
    app.api.report-date=2022-04-16
    ```

2. Configura tu `persistence.xml` con las credenciales de tu base de datos:
    ```xml
    <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/tu_basededatos"/>
    <property name="jakarta.persistence.jdbc.user" value="root"/>
    <property name="jakarta.persistence.jdbc.password" value="tu_contraseña"/>
    ```

3. Configura tu `log4j2.xml` para registrar logs tanto en consola como en archivo `.txt`:
    - Ejemplo de `FileAppender` en `log4j2.xml`:
    ```xml
    <File name="FileLogger" fileName="logs/applog.txt">
        <PatternLayout pattern="%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n" />
    </File>
    ```

---

##  Notas adicionales

- Puedes modificar `delay`, `iso` o la `fecha del reporte` directamente en `application.properties`.
- Asegúrate de que tu base de datos esté creada y accesible antes de ejecutar el proyecto.
- Todos los logs importantes quedan registrados en el archivo `logs/applog.txt`.

---

## Autor
- Brandon Morales


