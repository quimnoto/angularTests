package com.quim.angularTest.configuaration.auth.service;

import com.quim.angularTest.professor.ProfessorNotFoundException;
import com.quim.angularTest.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userManagementService")
public class AppUserDetailsService implements UserDetailsService {

  private ProfessorRepository professorRepository;

  @Autowired
  public AppUserDetailsService(ProfessorRepository professorRepository) {
    this.professorRepository = professorRepository;
  }



  @Override
  public UserDetails loadUserByUsername(String s) {
    return professorRepository.findByLogin(s)
      .map(myProfessor -> new User(myProfessor.getLogin(), myProfessor.getPassword(), AuthorityUtils.createAuthorityList(myProfessor.getRole().toString())))
      .orElseThrow(() -> new UsernameNotFoundException(String.format("The username %s doesn't exist", s)));
  }
}


