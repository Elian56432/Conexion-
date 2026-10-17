# API REST Escuelita (Spring Boot + PostgreSQL)

## Ejecutar
1. Crear la BD: `CREATE DATABASE escuelita;` (las tablas las crea Hibernate; también están en `docs/schema.sql`).
2. Usuario/contraseña: variables `DB_USER` / `DB_PASSWORD` (por defecto postgres/root).
3. `./gradlew bootRun` → http://localhost:8080

## Endpoints (CRUD completo)
`/api/departamentos`, `/api/profesores`, `/api/cursos`, `/api/estudiantes`, `/api/inscripciones`
(GET lista, GET /{id}, POST, PUT /{id}, DELETE /{id})

## Códigos HTTP
201 creado · 200 ok · 204 eliminado · 400 datos inválidos / FK inexistente · 404 no encontrado · 409 duplicado o borrado con registros relacionados

## Pruebas
Importar `docs/postman_collection.json` en Postman y ejecutar por carpeta en orden (Departamentos → Profesores → Cursos → Estudiantes → Inscripciones).
