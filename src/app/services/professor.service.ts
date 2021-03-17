import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { IProfessor } from '../model/profesor';

const API_URL = 'http://localhost:8080/professor/';
@Injectable({
	providedIn: 'root'
})
export class ProfessorService {

	constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

	getPofessors(): Observable<IProfessor[]> {
		console.log("get professor");
		return this.http.get<any>(API_URL + 'all', {headers: this.tokenService.getAuthorizationHeader()});
	}

	

}
