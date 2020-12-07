package com.quim.angularTest.configuaration;


import com.quim.angularTest.professor.Professor;
import com.quim.angularTest.professor.ProfessorRepository;
import com.quim.angularTest.professor.ProfessorRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(ProfessorRepository repository) {

    return args -> {
      repository.save(new Professor("Quim", "Noto","qnoto","quimNoto@test.com","630436129","$2a$10$l4mDEvxj1hVMx0g3jnlDHO9v0WrtpCm9mdai2xFUSSpuklpmyoT4a", ProfessorRole.USER));
      repository.save(new Professor("Cesc", "Noto","cnoto","cescNoto@test.com","630780881","$2a$10$l4mDEvxj1hVMx0g3jnlDHO9v0WrtpCm9mdai2xFUSSpuklpmyoT4a", ProfessorRole.ADMIN));
    };
  }
}

