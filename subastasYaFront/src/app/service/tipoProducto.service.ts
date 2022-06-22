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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http: HttpClient){}

  lista(): Observable<TipoProducto[]> {
    return this.http.get(this.tipoProductoURL + 'TipoProducto').pipe(
      map(response => response as TipoProducto[])
    );
  }

  createTipo(tipo: TipoProducto): Observable<any> {
    return this.http.post<any>(this.tipoProductoURL + 'createTipoProducto', tipo);
  }

  updateTipo(id: number, tipo: TipoProducto): Observable<TipoProducto> {
    // tslint:disable-next-line: max-line-length
    return this.http.put<TipoProducto>(`${this.tipoProductoURL}updateTipoProducto/${id}`, tipo,{headers: this.httpHeaders});
  }

  deleteTipo(id: number): Observable<TipoProducto> {
    return this.http.delete<TipoProducto>(`${this.tipoProductoURL}deleteTipoProducto/${id}`, {headers: this.httpHeaders})
  }


  /*
  getAnuncio(id: number): Observable<Anuncio>{
    return this.http.get<Anuncio>(`${this.anuncioURL}detailAnuncio/${id}`);
  }
  */

  
}