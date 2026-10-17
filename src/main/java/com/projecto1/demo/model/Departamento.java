package com.projecto1.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Columna 1: PK

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio") @Size(max = 100)
    private String nombre; // Columna 2: Nombre del departamento

    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "El código es obligatorio") @Size(max = 50)
    private String codigo; // Columna 3: Código único

    @Column(length = 255)
    @Size(max = 255)
    private String descripcion; // Columna 4: Descripción

    @Column(name = "presupuesto")
    @PositiveOrZero(message = "El presupuesto no puede ser negativo")
    private Double presupuesto; // Columna 5: Presupuesto asignado

    @Column(nullable = false)
    @NotNull(message = "activo es obligatorio")
    private Boolean activo = true; // Columna 6: Estado lógico

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion; // Columna 7: Auditoría

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    // Constructores, Getters y Setters
    public Departamento() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(Double presupuesto) { this.presupuesto = presupuesto; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}