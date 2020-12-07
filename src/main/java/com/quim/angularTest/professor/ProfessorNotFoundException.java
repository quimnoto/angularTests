package com.quim.angularTest.professor;

import java.util.UUID;

public class ProfessorNotFoundException extends RuntimeException {
  ProfessorNotFoundException(UUID id) {
    super("Could not find employee " + id);
  }
  ProfessorNotFoundException(String error) {
    super(error);
  }
}
