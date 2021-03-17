import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IAlumne } from '../model/alumne';
import { TokenStorageService } from './token-storage.service';



const API_URL = 'http://localhost:8080/alumne/';
@Injectable({
  providedIn: 'root'
})
export class AlumneService {

  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

	getAlumnes(): Observable<IAlumne[]> {
		console.log("get alumnes");		
		return this.http.get<any>(API_URL + 'all', {headers: this.tokenService.getAuthorizationHeader()});
	}

}
