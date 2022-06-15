import { Injectable } from '@angular/core';
import { Ciudad } from '../models/ciudad'
import {Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {

  ciudadURL = 'http://localhost:8080/ciudad/';

  constructor(private http: HttpClient){}

  lista(): Observable<Ciudad[]> {
    return this.http.get(this.ciudadURL + 'listaCiudades').pipe(
      map(response => response as Ciudad[])
    );
  }

  /*
  getAnuncio(id: number): Observable<Anuncio>{
    return this.http.get<Anuncio>(`${this.anuncioURL}detailAnuncio/${id}`);
  }
  */

  
}