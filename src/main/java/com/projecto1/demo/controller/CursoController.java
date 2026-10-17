package com.projecto1.demo.controller;

import com.projecto1.demo.model.Curso;
import com.projecto1.demo.model.Departamento;
import com.projecto1.demo.repository.CursoRepository;
import com.projecto1.demo.repository.DepartamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository cursos;
    private final DepartamentoRepository departamentos;

    public CursoController(CursoRepository cursos, DepartamentoRepository departamentos) {
        this.cursos = cursos;
        this.departamentos = departamentos;
    }

    private Departamento resolverDepartamento(Departamento ref) {
        if (ref == null || ref.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe indicar departamento.id");
        }
        return departamentos.findById(ref.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "El departamento " + ref.getId() + " no existe"));
    }

    @GetMapping
    public List<Curso> listar(@RequestParam(required = false) Long departamentoId) {
        return departamentoId == null ? cursos.findAll() : cursos.findByDepartamentoId(departamentoId);
    }

    @GetMapping("/{id}")
    public Curso obtener(@PathVariable Long id) {
        return cursos.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso crear(@Valid @RequestBody Curso body) {
        body.setId(null);
        body.setDepartamento(resolverDepartamento(body.getDepartamento()));
        return cursos.save(body);
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @Valid @RequestBody Curso body) {
        Curso c = obtener(id);
        c.setDepartamento(resolverDepartamento(body.getDepartamento()));
        c.setNombre(body.getNombre());
        c.setCodigoCurso(body.getCodigoCurso());
        c.setTemario(body.getTemario());
        c.setCreditos(body.getCreditos());
        c.setActivo(body.getActivo());
        return cursos.save(c);
    }

    /** Si tiene inscripciones asociadas, la BD rechaza el borrado y el handler responde 409. */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        obtener(id);
        cursos.deleteById(id);
    }
}
