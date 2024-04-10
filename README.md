# ms-usuarios-aplication

Este proyecto es una prueba de conocimientos requerida por el Banco BCI Miami. Implementa una API RESTful para la creación y gestión de usuarios, utilizando Spring Boot. La aplicación permite registrar usuarios nuevos, validar la unicidad del correo electrónico, y generar tokens JWT para la autenticación.

## Requisitos

Para ejecutar y probar este proyecto necesitas:

- Java 17
- Maven 3.6 o superior

## Instalación y Ejecución

Para instalar y ejecutar el proyecto, sigue estos pasos:
```bash
git clone https://github.com/David-Osorio-M/ms-usuarios-aplication.git
cd ms-usuarios-aplication
mvn spring-boot:run
```

La aplicación debería estar ahora corriendo y accesible en http://localhost:8080.

## Pruebas

Pruebas Unitarias 

Para ejecutar las pruebas unitarias del proyecto:
```bash
mvn test
```

## Probar la API con Postman

Para probar la funcionalidad de la API, puedes usar Postman:

Crear Usuario: Envía una solicitud POST a http://localhost:8080/usuarios/crear con el siguiente cuerpo JSON:
```json
{
    "nombre": "Juan Rodriguez",
    "correo": "juan@rodriguez.org",
    "contrasena": "hunter2",
    "telefonos": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

Espera recibir un código de estado 201 Created y un cuerpo de respuesta que incluya el ID de usuario, el token y otros campos relevantes.

## Documentación de la API con Swagger

Puedes acceder a la documentación interactiva de la API y probar los endpoints directamente desde Swagger UI en:
```html
http://localhost:8080/swagger-ui/index.html
```

## Tecnologías Utilizadas
El proyecto utiliza las siguientes tecnologías:

-Spring Boot para el framework de desarrollo.

-H2 Database como sistema de gestión de base de datos en memoria.

-JUnit y Mockito para las pruebas unitarias.

-Swagger/OpenAPI para la documentación de la API.

## Licencia
Este proyecto es una prueba de conocimientos y no está destinado para uso en producción.



