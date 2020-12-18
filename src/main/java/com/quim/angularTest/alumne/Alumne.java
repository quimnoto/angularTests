package com.quim.angularTest.alumne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Alumne {

  @Id
  @GeneratedValue
  private UUID id;
  private String nom;
  private String primerCognom;
  private String segonCognom;
  private String dni;
  private LocalDate dataNaixement;
  private String adreca;
  private String codiPostal;
  private String municipi;
  private String telefon;
  private String email;
  private String nomTutor1;
  private String nomTutor2;
  private String primerCognomTutor1;
  private String primerCognomTutor2;
  private String segonCognomTutor1;
  private String segonCognomTutor2;
  private String nacionalitat;
  private String paisNaixement;
  private String lleguaMaterna;
  private LocalDate dataArribada;
  private String curs;
  private String ralc;

}
