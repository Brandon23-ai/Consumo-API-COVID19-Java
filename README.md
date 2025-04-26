# Proyecto API COVID-19 - Consumo, Almacenamiento y Multihilo

Este proyecto desarrollado en **Java** realiza el consumo de una API pública de estadísticas de COVID-19, parsea la información y la guarda en una **base de datos** utilizando **JPA (Jakarta Persistence API)**. Además, utiliza **multithreading** y **Log4j2** para mejorar la gestión de procesos y el registro de logs.

---

##  Tecnologías utilizadas
- Java 17
- JPA (Jakarta Persistence API)
- Hibernate
- MySQL (o el motor de BD que configures)
- Log4j2
- GSON (Google para parseo JSON)
- Threads en Java
- Maven (gestor de dependencias)
  
---

##  Estructura del Proyecto
- `models/` → Entidades JPA para la base de datos.
- `dtos/` → Objetos de transferencia de datos para parsear la API.
- `services/` → Lógica de negocio para consumir la API y guardar en la base de datos.
- `threads/` → Hilos para consumo automático después de un delay.
- `META-INF/persistence.xml` → Configuración de conexión a la base de datos.
- `application.properties` → Configuración del tiempo de espera, ISO del país y fecha de reporte.
- `log4j2.xml` → Configuración de Log4j2 para manejo de logs.

---

##  ¿Cómo funciona?
1. **Al iniciar el proyecto**, un hilo (`ApiDataLoader`) espera los segundos configurados en `application.properties`.
2. Luego de esperar, **consume** en orden:
   - **Regiones** (`regions` endpoint)
   - **Provincias** (`provinces` endpoint)
   - **Reportes** (`reports` endpoint)
3. **Guarda** toda la información en tu base de datos local o remota.
4. **Genera logs** para el monitoreo de procesos exitosos o fallidos.

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

3. Configura tu `log4j2.xml` para que los logs aparezcan en consola y/o en archivos.

---

##  Requisitos Especiales

- El **consumo de la API** se hace 15 segundos después de iniciada la app.
- Se deben insertar datos **solo una vez** en cada ejecución.
- Se utiliza **Log4j2** para registrar eventos de la aplicación.
- Se guardan los logs en un archivo txt ubicado en el archivo principal.
  
---

##  Notas adicionales
- Puedes cambiar el `delay`, el `iso` o la `fecha del reporte` directamente en `application.properties`.
- Asegúrate de que tu base de datos esté **creada** antes de ejecutar el proyecto.
- Si quieres que los logs también se guarden en archivo, edita `log4j2.xml` y agrega un `FileAppender`.

---

## Autor y colaboradores
**Brandon Morales**  
**Yamilet Franco**

---


