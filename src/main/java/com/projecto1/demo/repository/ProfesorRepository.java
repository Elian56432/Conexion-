
package com.projecto1.demo.repository;

import com.projecto1.demo.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    // Método auxiliar para consultar por clave foránea
    List<Profesor> findByDepartamentoId(Long departamentoId);
}