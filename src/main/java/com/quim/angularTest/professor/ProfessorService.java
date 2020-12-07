package com.quim.angularTest.professor;

import com.quim.angularTest.configuaration.auth.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

  private final ProfessorRepository professorRepository;
  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;
  private final PasswordEncoder passwordEncoder;


  public ProfessorService(ProfessorRepository professorRepository, AuthenticationManager authenticationManager, TokenProvider tokenProvider, PasswordEncoder passwordEncoder){
    this.professorRepository =  professorRepository;
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
    this.passwordEncoder = passwordEncoder;
  }

  public Professor primerProfessor() {
    return professorRepository.findAll().get(0);
  }

  public List<Professor> totsElsProfessors() {
    return professorRepository.findAll();
  }

  public Professor save(Professor newProfessor) {
    return professorRepository.save(newProfessor);
  }

  public Optional<Professor> buscarPerId(UUID id) {
    return professorRepository.findById(id);
  }

  public void borrarProfessor(UUID id) {
    professorRepository.deleteById(id);
  }

  public String authenticateUserAndGetToken(String login, String password) {
    final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.generateToken(authentication);
  }
}
