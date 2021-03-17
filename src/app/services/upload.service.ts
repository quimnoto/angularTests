import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const API_URL = 'http://localhost:8080/alumne/';
@Injectable({
  providedIn: 'root'
})
export class UploadService {


  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', API_URL + 'carregar', formData, {
      reportProgress: true,
      responseType: 'json',
      headers: this.tokenService.getAuthorizationHeader()
    });

    return this.http.request(req);

   /* return this.http.post<any>(API_URL + 'carregar', formData, {
      reportProgress: true,
      responseType: 'json'
    });*/
  }
}
