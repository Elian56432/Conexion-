-- Estructura de la base de datos "escuelita" (PostgreSQL). Equivale a lo que genera Hibernate.
-- Normalización: 3FN. Inscripciones resuelve la relación N:M entre estudiantes y cursos.

CREATE TABLE departamentos (
    id             BIGSERIAL PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    codigo         VARCHAR(50)  NOT NULL UNIQUE,
    descripcion    VARCHAR(255),
    presupuesto    DOUBLE PRECISION CHECK (presupuesto >= 0),
    activo         BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion TIMESTAMP
);

CREATE TABLE profesores (
    id                 BIGSERIAL PRIMARY KEY,
    nombre             VARCHAR(60)  NOT NULL,
    apellido           VARCHAR(60)  NOT NULL,
    email              VARCHAR(100) NOT NULL UNIQUE,
    telefono           VARCHAR(15),
    fecha_contratacion DATE,
    departamento_id    BIGINT NOT NULL REFERENCES departamentos(id)
);

CREATE TABLE cursos (
    id              BIGSERIAL PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    codigo_curso    VARCHAR(20)  NOT NULL UNIQUE,
    temario         TEXT,
    creditos        INTEGER NOT NULL,
    activo          BOOLEAN NOT NULL DEFAULT TRUE,
    departamento_id BIGINT NOT NULL REFERENCES departamentos(id)
);

CREATE TABLE estudiantes (
    id                   BIGSERIAL PRIMARY KEY,
    nombre               VARCHAR(50)  NOT NULL,
    apellido             VARCHAR(50)  NOT NULL,
    correo_electronico   VARCHAR(100) NOT NULL UNIQUE,
    telefono             VARCHAR(15),
    fecha_nacimiento     DATE,
    promedio_acumulado   DOUBLE PRECISION NOT NULL
);

CREATE TABLE inscripciones (
    id                 BIGSERIAL PRIMARY KEY,
    fecha_inscripcion  DATE NOT NULL,
    estado             VARCHAR(20) NOT NULL,
    calificacion_final DOUBLE PRECISION,
    semestre_cursado   VARCHAR(10) NOT NULL,
    estudiante_id      BIGINT NOT NULL REFERENCES estudiantes(id),
    curso_id           BIGINT NOT NULL REFERENCES cursos(id),
    CONSTRAINT uk_inscripcion_est_curso_sem UNIQUE (estudiante_id, curso_id, semestre_cursado)
);
