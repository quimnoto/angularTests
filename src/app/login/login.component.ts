import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiGlobalsService } from '../service/global.service';
import { ProfessorService } from '../service/professor.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginError = false;
  credentials = {username: '', password: ''};

  constructor(private app: ProfessorService, private http: HttpClient, private router: Router, protected apiGlobalsService: ApiGlobalsService) {
  }

  ngOnInit(): void {
  }
  login() {
    this.app.authenticate(this.credentials)
      .subscribe((response) => {
        this.apiGlobalsService.bearer = response.headers.get('Authorization');
        this.router.navigateByUrl('/home');
      }, 
      () => this.loginError=true);
    
  }

  

}








