package com.quim.angularTest.alumne;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AlumneServie {
  private final AlumneRepository alumneRepository;


  public AlumneServie(AlumneRepository alumneRepository) {
    this.alumneRepository = alumneRepository;
  }

  public void carregaAlumneDeExcel(MultipartFile file){
    try {
      List<Alumne> alumnesList = ExcelHelper.excelToAlumnes(file.getInputStream());
      alumnesList.stream().forEach(alumne ->{
        acualitzarInsertarAlumne(alumne);
      });

      alumneRepository.saveAll(alumnesList);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  private void acualitzarInsertarAlumne(Alumne alumne) {
    alumneRepository.findByRalc(alumne.getRalc()).ifPresentOrElse(
      alumneOptional -> {
        alumne.setId(alumneOptional.getId());
        alumneRepository.save(alumne);
      },
      ()-> alumneRepository.save(alumne));
  }

  public List<Alumne> totsElsAlumnes() {
    return alumneRepository.findAll();
  }
}
