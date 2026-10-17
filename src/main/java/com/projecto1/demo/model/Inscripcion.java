package com.projecto1.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscripciones", uniqueConstraints = @UniqueConstraint(
        name = "uk_inscripcion_est_curso_sem",
        columnNames = {"estudiante_id", "curso_id", "semestre_cursado"}))
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Columna 1: PK

    @Column(name = "fecha_inscripcion", nullable = false)
    @NotNull(message = "La fecha de inscripción es obligatoria")
    private LocalDate fechaInscripcion; // Columna 2

    @Column(nullable = false, length = 20)
    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "ACTIVO|RETIRADO|APROBADO|REPROBADO", message = "Estado válido: ACTIVO, RETIRADO, APROBADO o REPROBADO")
    private String estado; // Columna 3 (Ej: "ACTIVO", "RETIRADO")

    @Column(name = "calificacion_final")
    @DecimalMin("0.0") @DecimalMax("10.0")
    private Double calificacionFinal; // Columna 4

    @Column(name = "semestre_cursado", nullable = false, length = 10)
    @NotBlank(message = "El semestre es obligatorio") @Size(max = 10)
    private String semestreCursado; // Columna 5

    // Columna 6: FK hacia Estudiante
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estudiante_id", nullable = false)
    @NotNull(message = "El estudiante es obligatorio")
    private Estudiante estudiante;

    // Columna 7: FK hacia Curso
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull(message = "El curso es obligatorio")
    private Curso curso;

    public Inscripcion() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Double getCalificacionFinal() { return calificacionFinal; }
    public void setCalificacionFinal(Double calificacionFinal) { this.calificacionFinal = calificacionFinal; }
    public String getSemestreCursado() { return semestreCursado; }
    public void setSemestreCursado(String semestreCursado) { this.semestreCursado = semestreCursado; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}