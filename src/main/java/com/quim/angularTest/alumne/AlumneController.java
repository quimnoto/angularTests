package com.quim.angularTest.alumne;

import com.quim.angularTest.message.ResponseMessage;
import com.quim.angularTest.professor.Professor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class AlumneController {

  private final AlumneServie alumneServie;


  public AlumneController(AlumneServie alumneServie) {
    this.alumneServie = alumneServie;
  }
  @PostMapping("/alumne/carregar")
  public ResponseEntity<ResponseMessage> carregarAlumnes(@RequestParam("file") MultipartFile file) {
    String message = "";

    //if (ExcelHelper.hasExcelFormat(file)) {
      try {
        alumneServie.carregaAlumneDeExcel(file);


        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    /*}

    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);*/
  }

  @GetMapping("/alumne/all")
  List<Alumne> totsalumnes() {
    return alumneServie.totsElsAlumnes();
  }
}
