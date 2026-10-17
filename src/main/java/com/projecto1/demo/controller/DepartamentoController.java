package com.projecto1.demo.controller;

import com.projecto1.demo.model.Departamento;
import com.projecto1.demo.repository.DepartamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoRepository departamentos;

    public DepartamentoController(DepartamentoRepository departamentos) {
        this.departamentos = departamentos;
    }

    @GetMapping
    public List<Departamento> listar() {
        return departamentos.findAll();
    }

    @GetMapping("/{id}")
    public Departamento obtener(@PathVariable Long id) {
        return departamentos.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@Valid @RequestBody Departamento body) {
        body.setId(null); // evita que un POST con "id" sobrescriba un registro existente
        return departamentos.save(body);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @Valid @RequestBody Departamento body) {
        Departamento d = obtener(id);
        d.setNombre(body.getNombre());
        d.setCodigo(body.getCodigo());
        d.setDescripcion(body.getDescripcion());
        d.setPresupuesto(body.getPresupuesto());
        d.setActivo(body.getActivo());
        return departamentos.save(d);
    }

    /** Si tiene profesores o cursos asociados, la BD rechaza el borrado y el handler responde 409. */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        obtener(id);
        departamentos.deleteById(id);
    }
}
