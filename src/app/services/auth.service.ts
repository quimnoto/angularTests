import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponseBase, HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';



const AUTH_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  defaultHeaders = new HttpHeaders();

  constructor(private httpClient: HttpClient) { }

  public authenticate(credentials): Observable<HttpResponseBase> {
    const headers = this.defaultHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
		return this.httpClient.post(AUTH_API + 'login', {
      login: credentials.username,
      password: credentials.password
    }, {
			headers: headers,
			observe: 'response'
		});
  }
  
}
