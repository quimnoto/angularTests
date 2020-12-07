package com.quim.angularTest.professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
  Optional<Professor> findByLogin(@Param("login") String login);
}
