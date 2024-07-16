<h1> 🏆 API REST FOROHUB </h1>


Descripción del Proyecto: API REST para un Foro de Tópicos

Este proyecto consiste en el desarrollo de una API RESTful utilizando Spring Boot para la gestión de un foro de tópicos. La API permitirá a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los tópicos del foro. Este sistema servirá como backend para una aplicación web o móvil donde los usuarios pueden interactuar con el foro.

<h2> ✏️ Funcionalidades </h2>

⚫ Gestión de Tópicos:

- Creación de nuevos tópicos.
- Lectura de tópicos existentes.
- Actualización de contenido de tópicos.
- Eliminación de tópicos.

  ![image](https://github.com/user-attachments/assets/dd047ce3-5499-467d-86bb-3b79675f5550)


⚫ Seguridad:

Seguridad con JWT (JSON Web Tokens) para la autenticación y autorización.
Manejo de autenticacion STATELESS

![image](https://github.com/user-attachments/assets/e41ad68a-aa68-4a2b-9c9f-2ec50eb4f0f5)


⚫ End points:

- POST /login
- POST /topicos - Crear nuevo tópico.
- GET /topicos - Listar todos los tópicos.
- GET /topicos/{id} - Obtener detalles de un tópico.
- PUT /topicos/{id} - Actualizar un tópico.
- DELETE /topicos/{id} - Eliminar un tópico.


<h2> 💻 Tecnología Utilizada </h2>

✔️ Java: Lenguaje de programación principal utilizado para el desarrollo de la aplicación.

✔️ Spring: Data JPA, WEB, SECURITY y VALIDATION.

✔️ Base de Datos: MySQL para almacenar la información de libros y autores.

✔️ Autenticación y Autorización: JWT (JSON Web Tokens)



<h2> 🟤 Objetivos del Proyecto: </h2>

Crear una API robusta y escalable para la gestión de un foro de tópicos.
Implementar un sistema seguro de autenticación y autorización.
Facilitar la moderación y gestión del contenido por parte de los administradores y moderadores.
Proveer una documentación clara y detallada de la API para los desarrolladores que interactúen con ella.
