package com.projecto1.demo.repository;

import com.projecto1.demo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByDepartamentoId(Long departamentoId);
}
