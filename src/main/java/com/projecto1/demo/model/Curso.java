package com.projecto1.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Columna 1: PK

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio") @Size(max = 100)
    private String nombre; // Columna 2

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "El código del curso es obligatorio") @Size(max = 20)
    private String codigoCurso; // Columna 3

    @Column(columnDefinition = "TEXT")
    private String temario; // Columna 4

    @Column(nullable = false)
    @NotNull(message = "Los créditos son obligatorios") @Min(value = 1, message = "Mínimo 1 crédito") @Max(value = 30, message = "Máximo 30 créditos")
    private Integer creditos; // Columna 5

    @Column(nullable = false)
    @NotNull(message = "activo es obligatorio")
    private Boolean activo = true; // Columna 6

    // Columna 7: Clave Foránea hacia Departamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id", nullable = false)
    @NotNull(message = "El departamento es obligatorio")
    private Departamento departamento;

    public Curso() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigoCurso() { return codigoCurso; }
    public void setCodigoCurso(String codigoCurso) { this.codigoCurso = codigoCurso; }
    public String getTemario() { return temario; }
    public void setTemario(String temario) { this.temario = temario; }
    public Integer getCreditos() { return creditos; }
    public void setCreditos(Integer creditos) { this.creditos = creditos; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
}