package com.quim.angularTest.alumne;


import com.quim.angularTest.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AlumneRepository extends JpaRepository<Alumne, UUID> {
  Optional<Alumne> findByRalc(@Param("ralc") String ralc);
}
