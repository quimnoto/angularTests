import { Component, OnInit } from '@angular/core';
import { UploadService } from '../services/upload.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IAlumne } from '../model/alumne';
import { AlumneService } from '../services/alumne.service';

@Component({
  selector: 'app-alumnes',
  templateUrl: './alumnes.component.html',
  styleUrls: ['./alumnes.component.css']
})
export class AlumnesComponent implements OnInit {

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';

  alumnesInfo: IAlumne[] = [];

  constructor(private uploadService: UploadService, private alumneService: AlumneService) { }

  ngOnInit() {
    this.alumneService.getAlumnes().subscribe(
      (data: IAlumne[]) => {
        console.log('Data size: ' + data.length);
        if(data){
          this.alumnesInfo = data;
        }else {
          let alumne:IAlumne;
          alumne.nom = 'tets';
          alumne.primerCognom = 'test2';
          this.alumnesInfo.push(alumne);
        }      
      },err => {        
        this.message = 'Error Get Alumens';
        console.log(err);
        //this.currentFile = undefined;
      });
    console.log('Alumens size: ' + this.alumnesInfo.length);
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;
  
    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          console.log('upload ok');
          this.progress = 100;
          this.message = event.body.message;
          this.alumneService.getAlumnes().subscribe((data: IAlumne[]) => this.alumnesInfo =data);
          this.currentFile = undefined; 
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });
  
    this.selectedFiles = undefined;
  }

  getAlumenes(){
    this.alumneService.getAlumnes().subscribe(
      (data: IAlumne[]) => {
        console.log('Data size: ' + data.length);
        if(data){
          this.alumnesInfo = data;
        }else {
          let alumne:IAlumne;
          alumne.nom = 'tets';
          alumne.primerCognom = 'test2';
          this.alumnesInfo.push(alumne);
        }      
      },err => {        
        this.message = 'Error Get Alumens';
        console.log(err);
        //this.currentFile = undefined;
      });
  }

}
