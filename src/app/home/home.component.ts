import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IProfessor} from '../model/profesor';
import { ProfessorService } from '../services/professor.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'Demo';
  professor:IProfessor;
  professors: IProfessor[];

  constructor(private profService: ProfessorService) {      
    
  }

  ngOnInit(): void {
    this.profService.getPofessors().subscribe((data: IProfessor[]) => this.professors = data);
  }

  

}













