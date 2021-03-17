package com.quim.angularTest.alumne;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
  static String SHEET = "Tutorials";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Alumne> excelToAlumnes(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);

      //Sheet sheet = workbook.getSheet(SHEET);
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rows = sheet.iterator();

      List<Alumne> alumnes = new ArrayList<Alumne>();
      final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber < 6) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Alumne alumne = new Alumne();
        int cellIdx = 0;
        String adreca = "";
        String dataNaixement = "";
        String dataArribada = "";
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
            case 1:
              alumne.setNom(currentCell.getStringCellValue());
              break;
            case 2:
              alumne.setPrimerCognom(currentCell.getStringCellValue());
              break;
            case 3:
              alumne.setSegonCognom(currentCell.getStringCellValue());
              break;
            case 4:
              alumne.setDni(currentCell.getStringCellValue());
              break;
            case 5:
              dataNaixement = currentCell.getStringCellValue();
              if(!dataNaixement.isEmpty()) {
                alumne.setDataNaixement(LocalDate.parse(dataNaixement, dtf));
              }
              break;
            case 6:
              adreca += currentCell.getStringCellValue();
              break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
              if(!currentCell.getStringCellValue().isEmpty()) {
                adreca = adreca + " " + currentCell.getStringCellValue();
              }
              break;
            case 13:
              alumne.setCodiPostal(currentCell.getStringCellValue());
              break;
            case 14:
              alumne.setMunicipi(currentCell.getStringCellValue());
              break;
            case 15:
                alumne.setTelefon(currentCell.getStringCellValue());
                break;
            case 16:
              alumne.setEmail(currentCell.getStringCellValue());
              break;
            case 17:
              alumne.setNomTutor1(currentCell.getStringCellValue());
              break;
            case 18:
              alumne.setPrimerCognomTutor1(currentCell.getStringCellValue());
              break;
            case 19:
              alumne.setSegonCognomTutor1(currentCell.getStringCellValue());
              break;
            case 20:
              alumne.setNomTutor2(currentCell.getStringCellValue());
              break;
            case 21:
              alumne.setPrimerCognomTutor2(currentCell.getStringCellValue());
              break;
            case 22:
              alumne.setSegonCognomTutor2(currentCell.getStringCellValue());
              break;
            case 23:
              alumne.setNacionalitat(currentCell.getStringCellValue());
              break;
            case 24:
              alumne.setPaisNaixement(currentCell.getStringCellValue());
              break;
            case 25:
              alumne.setLleguaMaterna(currentCell.getStringCellValue());
              break;
            case 26:
              dataArribada = currentCell.getStringCellValue();
              if(!dataArribada.isEmpty()) {
                alumne.setDataArribada(LocalDate.parse(dataArribada, dtf));
              }

              break;
            case 27:
              String curs = currentCell.getStringCellValue();

              alumne.setCurs(curs);
              break;
            case 28:
              alumne.setRalc(currentCell.getStringCellValue());
              break;
            default:
              break;
          }

          cellIdx++;
        }

        alumne.setAdreca(adreca);

        alumnes.add(alumne);
      }

      workbook.close();

      return alumnes;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }

  private String transformarCurs(String cursExcel) {
    String cursApplicacio ="";
    switch(cursExcel){
      case "EPRILOEM301":
        cursApplicacio="P3A";
        break;

    }
    return cursApplicacio;
    //Curs curs = buscarCursPerNom(curs);

  }

}
