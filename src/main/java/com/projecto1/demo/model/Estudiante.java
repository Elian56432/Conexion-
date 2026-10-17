package com.projecto1.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Columna 1: PK

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio") @Size(max = 50)
    private String nombre; // Columna 2

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido es obligatorio") @Size(max = 50)
    private String apellido; // Columna 3

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "El correo es obligatorio") @Email(message = "Correo inválido") @Size(max = 100)
    private String correoElectronico; // Columna 4

    @Column(length = 15)
    @Size(max = 15)
    private String telefono; // Columna 5

    @Column(name = "fecha_nacimiento")
    @Past(message = "La fecha de nacimiento debe ser pasada")
    private LocalDate fechaNacimiento; // Columna 6

    @Column(nullable = false)
    @NotNull(message = "El promedio es obligatorio") @DecimalMin("0.0") @DecimalMax("10.0") // ajusta el rango si usas escala 0-100
    private Double promedioAcumulado; // Columna 7

    public Estudiante() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Double getPromedioAcumulado() { return promedioAcumulado; }
    public void setPromedioAcumulado(Double promedioAcumulado) { this.promedioAcumulado = promedioAcumulado; }
}