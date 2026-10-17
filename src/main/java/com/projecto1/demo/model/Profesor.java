package com.projecto1.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Columna 1: PK

    @Column(nullable = false, length = 60)
    @NotBlank(message = "El nombre es obligatorio") @Size(max = 60)
    private String nombre; // Columna 2

    @Column(nullable = false, length = 60)
    @NotBlank(message = "El apellido es obligatorio") @Size(max = 60)
    private String apellido; // Columna 3

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "El email es obligatorio") @Email(message = "Email inválido") @Size(max = 100)
    private String email; // Columna 4

    @Column(length = 15)
    @Size(max = 15)
    private String telefono; // Columna 5

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion; // Columna 6

    // Columna 7: Relación de clave foránea hacia Departamento (Integridad Referencial)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id", nullable = false)
    @NotNull(message = "El departamento es obligatorio")
    private Departamento departamento;

    // Constructores, Getters y Setters
    public Profesor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
}