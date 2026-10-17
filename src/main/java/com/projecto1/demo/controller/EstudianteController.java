package com.projecto1.demo.controller;

import com.projecto1.demo.model.Estudiante;
import com.projecto1.demo.repository.EstudianteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudiantes;

    public EstudianteController(EstudianteRepository estudiantes) {
        this.estudiantes = estudiantes;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudiantes.findAll();
    }

    @GetMapping("/{id}")
    public Estudiante obtener(@PathVariable Long id) {
        return estudiantes.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudiante crear(@Valid @RequestBody Estudiante body) {
        body.setId(null);
        return estudiantes.save(body);
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @Valid @RequestBody Estudiante body) {
        Estudiante e = obtener(id);
        e.setNombre(body.getNombre());
        e.setApellido(body.getApellido());
        e.setCorreoElectronico(body.getCorreoElectronico());
        e.setTelefono(body.getTelefono());
        e.setFechaNacimiento(body.getFechaNacimiento());
        e.setPromedioAcumulado(body.getPromedioAcumulado());
        return estudiantes.save(e);
    }

    /** Si tiene inscripciones asociadas, la BD rechaza el borrado y el handler responde 409. */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        obtener(id);
        estudiantes.deleteById(id);
    }
}
