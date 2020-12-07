import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable()
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  

  /*getHeroes(): Observable<IProfessor[]> {
      // TODO: send the message _after_ fetching the heroes
      this.messageService.add('HeroService: fetched heroes');
      return of(HEROES);
  }*/

}
