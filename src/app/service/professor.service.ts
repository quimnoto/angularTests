import { HttpClient, HttpParams, HttpResponseBase } from '@angular/common/http';
import { Inject, Injectable, Optional } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseService } from './base.service';
import { ApiGlobalsService } from './global.service';
import { BASE_PATH } from './variables';

@Injectable({
	providedIn: 'root'
})
export class ProfessorService extends BaseService {

	constructor(protected httpClient: HttpClient, protected apiGlobalsService: ApiGlobalsService,
		@Optional() @Inject(BASE_PATH) basePath: string) {
		super(basePath, apiGlobalsService);
	}

	public authenticate(credentials): Observable<HttpResponseBase> {

		const headers = this.defaultHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
		const body = new HttpParams().set('login', credentials.username).set('password', credentials.password);


		return this.httpClient.post<HttpResponseBase>(`/login`, {
        login: credentials.username,
        password: credentials.password
      }, {
			headers: headers,
			observe: 'response'
		});
	}

}
