import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import {of } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuarioURL = 'http://localhost:8080/usuario/';

  constructor(private http: HttpClient){}


  lista(): Observable<Usuario[]> {
    return this.http.get(this.usuarioURL + 'get-users').pipe(
      map(response => response as Usuario[])
    );
  }

  getUsuario(id:number): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.usuarioURL}detail-user/${id}`);
  }


}