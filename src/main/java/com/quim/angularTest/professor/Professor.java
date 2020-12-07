package com.quim.angularTest.professor;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor {

  @Id
  @GeneratedValue
  private UUID id;
  private String nom;
  private String cognom;
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "user_login", length = 10, nullable = false, unique = true)
  private String login;

  @NotNull
  @Size(min = 1, max = 256)
  @Column(name = "user_password", length = 256, nullable = false)
  private String password;
  private String email;
  private String telefon;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_role")
  @NotNull
  private ProfessorRole role;

  public Professor(String nom, String cognom, String login,String email, String telefon, String password, ProfessorRole professorRole) {
    this.nom = nom;
    this.cognom = cognom;
    this.login = login;
    this.email = email;
    this.telefon = telefon;
    this.password = password;
    this.role = professorRole;
  }
}
