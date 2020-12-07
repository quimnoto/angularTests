package com.quim.angularTest.alumne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
  private String cognom;

}
