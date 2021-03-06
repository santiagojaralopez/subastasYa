import { Injectable } from '@angular/core';
import { Anuncio } from '../models/anuncio';
import {Observable } from 'rxjs';
import {of } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AnuncioService {

  anuncioURL = 'http://localhost:8080/anuncio/';

  constructor(private http: HttpClient){}

  lista(): Observable<Anuncio[]> {
    return this.http.get(this.anuncioURL + 'listaAnuncio').pipe(
      map(response => response as Anuncio[])
    );
  }

  listByUser(username: string): Observable<Anuncio[]> {
    return this.http.get(this.anuncioURL + 'listaAnunciosUser/'+username).pipe(
      map(response => response as Anuncio[])
    )
  }

  getAnuncio(id: number): Observable<Anuncio>{
    return this.http.get<Anuncio>(`${this.anuncioURL}detailAnuncio/${id}`);
  }

  listaActivos(): Observable<Anuncio[]> {
    return this.http.get(this.anuncioURL + 'listaAnuncioActivos').pipe(
      map(response => response as Anuncio[])
    );
  }

  createAnuncio(anuncio: Anuncio): Observable<any> {
    return this.http.post<any>(this.anuncioURL + 'createAnuncio', anuncio);
  }


  updateAnuncioBloqueo(anuncio: Anuncio): Observable<Anuncio>{
    return this.http.put<Anuncio>(`${this.anuncioURL}updateAnuncioBloqueo/${anuncio.id_anuncio}`, anuncio);
  }

  updateAnuncioActivar(anuncio: Anuncio): Observable<Anuncio>{
    return this.http.put<Anuncio>(`${this.anuncioURL}updateAnuncioActivar/${anuncio.id_anuncio}`, anuncio);
  }

  updateAnuncioInactivo(anuncio: Anuncio): Observable<Anuncio>{
    return this.http.put<Anuncio>(`${this.anuncioURL}updateAnuncioInactivo/${anuncio.id_anuncio}`, anuncio);
  }

  updateAnuncioVendido(anuncio: Anuncio): Observable<Anuncio>{
    return this.http.put<Anuncio>(`${this.anuncioURL}updateAnuncioVendido/${anuncio.id_anuncio}`, anuncio);
  }
  

  




}