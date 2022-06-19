import { Injectable } from '@angular/core';
import { TipoProducto } from '../models/tipoProducto'
import {Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TipoProductoService {

  tipoProductoURL = 'http://localhost:8080/tipoProducto/';

  constructor(private http: HttpClient){}

  lista(): Observable<TipoProducto[]> {
    return this.http.get(this.tipoProductoURL + 'TipoProducto').pipe(
      map(response => response as TipoProducto[])
    );
  }

  /*
  getAnuncio(id: number): Observable<Anuncio>{
    return this.http.get<Anuncio>(`${this.anuncioURL}detailAnuncio/${id}`);
  }
  */

  
}