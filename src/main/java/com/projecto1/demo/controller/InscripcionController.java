package com.projecto1.demo.controller;

import com.projecto1.demo.model.Curso;
import com.projecto1.demo.model.Estudiante;
import com.projecto1.demo.model.Inscripcion;
import com.projecto1.demo.repository.CursoRepository;
import com.projecto1.demo.repository.EstudianteRepository;
import com.projecto1.demo.repository.InscripcionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionRepository inscripciones;
    private final EstudianteRepository estudiantes;
    private final CursoRepository cursos;

    public InscripcionController(InscripcionRepository inscripciones,
                                 EstudianteRepository estudiantes,
                                 CursoRepository cursos) {
        this.inscripciones = inscripciones;
        this.estudiantes = estudiantes;
        this.cursos = cursos;
    }

    private Estudiante resolverEstudiante(Estudiante ref) {
        if (ref == null || ref.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe indicar estudiante.id");
        }
        return estudiantes.findById(ref.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estudiante " + ref.getId() + " no existe"));
    }

    private Curso resolverCurso(Curso ref) {
        if (ref == null || ref.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe indicar curso.id");
        }
        Curso c = cursos.findById(ref.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "El curso " + ref.getId() + " no existe"));
        if (!Boolean.TRUE.equals(c.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El curso " + c.getId() + " está inactivo");
        }
        return c;
    }

    @GetMapping
    public List<Inscripcion> listar(@RequestParam(required = false) Long estudianteId,
                                    @RequestParam(required = false) Long cursoId) {
        if (estudianteId != null) return inscripciones.findByEstudianteId(estudianteId);
        if (cursoId != null) return inscripciones.findByCursoId(cursoId);
        return inscripciones.findAll();
    }

    @GetMapping("/{id}")
    public Inscripcion obtener(@PathVariable Long id) {
        return inscripciones.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscripción no encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inscripcion crear(@Valid @RequestBody Inscripcion body) {
        body.setId(null);
        Estudiante e = resolverEstudiante(body.getEstudiante());
        Curso c = resolverCurso(body.getCurso());
        if (inscripciones.existsByEstudianteIdAndCursoIdAndSemestreCursado(e.getId(), c.getId(), body.getSemestreCursado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El estudiante ya está inscrito en ese curso para el semestre indicado");
        }
        body.setEstudiante(e);
        body.setCurso(c);
        return inscripciones.save(body);
    }

    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @Valid @RequestBody Inscripcion body) {
        Inscripcion i = obtener(id);
        i.setEstudiante(resolverEstudiante(body.getEstudiante()));
        i.setCurso(resolverCurso(body.getCurso()));
        i.setFechaInscripcion(body.getFechaInscripcion());
        i.setEstado(body.getEstado());
        i.setCalificacionFinal(body.getCalificacionFinal());
        i.setSemestreCursado(body.getSemestreCursado());
        return inscripciones.save(i); // duplicado (estudiante+curso+semestre) -> 409 por la constraint única
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        obtener(id);
        inscripciones.deleteById(id);
    }
}
