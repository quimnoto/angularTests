import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { IProfessor} from '../model/profesor';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'Demo';
  professor:IProfessor;
  professors: IProfessor[];

  constructor(private app: AppService, private http: HttpClient) {
      http.get('professor').subscribe((data: IProfessor[]) => this.professors = data);
  }

  ngOnInit(): void {
  }

  authenticated() {
    return this.app.authenticated;
  }

}













