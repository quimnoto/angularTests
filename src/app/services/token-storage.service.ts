import { HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  public defaultHeaders = new HttpHeaders();
  bearer: string;

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public getAuthorizationHeader() {
    if(this.bearer){
      return this.defaultHeaders.set('Authorization', this.bearer);
    }
      return this.defaultHeaders;
    }
		

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    this.bearer = token;
  }

  public getToken(): string {
    return this.bearer;
  }

  public saveUser(user): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }

  public getOptions(): {
		headers?: HttpHeaders | {
			[header: string]: string | string[];
		};
		observe?: 'body';
		params?: HttpParams | {
			[param: string]: string | string[];
		};
		reportProgress?: boolean;
		responseType?: 'json';
		withCredentials?: boolean;
	} {
		return { headers: this.getAuthorizationHeader()};
	}
}
