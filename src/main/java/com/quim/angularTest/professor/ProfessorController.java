package com.quim.angularTest.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProfessorController {

  private final ProfessorService professorService;

  @Autowired
  ProfessorController(ProfessorService professorService) {
    this.professorService = professorService;
  }


  /*@GetMapping("/professors")
  Professor getProfessor() {
    return professorService.primerProfessor();
  }*/
  @GetMapping("/professor/all")
  List<Professor> totsProfessors() {
    return professorService.totsElsProfessors();
  }

  @PostMapping("/professors")
  Professor nouProfessor(@RequestBody Professor newProfessor) {
    return professorService.save(newProfessor);
  }

  // Single item

  @GetMapping("/professors/{id}")
  Professor one(@PathVariable UUID id) {

    return professorService.buscarPerId(id)
      .orElseThrow(() -> new ProfessorNotFoundException(id));
  }

  @PutMapping("/Professors/{id}")
  Professor replaceProfessor(@RequestBody Professor newProfessor, @PathVariable UUID id) {

    return professorService.buscarPerId(id)
      .map(professor -> {
        professor.setNom(newProfessor.getNom());
        return professorService.save(professor);
      })
      .orElseGet(() -> {
        newProfessor.setId(id);
        return professorService.save(newProfessor);
      });
  }

  @DeleteMapping("/Professors/{id}")
  void deleteProfessor(@PathVariable UUID id) {
    professorService.borrarProfessor(id);
  }

  /*@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity authenticateUser(@RequestParam("login") String login, @RequestParam("password") String password) {
    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + this.professorService.authenticateUserAndGetToken(login, password)).build();
  }*/
}
