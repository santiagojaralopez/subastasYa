import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import {of } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/usuario';
import { UpdateUsuarioDTO } from '../models/update-user-dto';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private usuarioURL = 'http://localhost:8080/usuario/';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient){}


  lista(): Observable<Usuario[]> {
    return this.http.get(this.usuarioURL + 'get-users').pipe(
      map(response => response as Usuario[])
    );
  }

  getUsuario(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.usuarioURL}/detail-user/${id}`);
  }

  getUsuarioByUserName(userName: string): Observable<UpdateUsuarioDTO> {
    return this.http.get<UpdateUsuarioDTO>(`${this.usuarioURL}/detail-user/${userName}`);
  }

  updateUser(nombreUsuario: string, updateUserDTO: UpdateUsuarioDTO): Observable<UpdateUsuarioDTO> {
    // tslint:disable-next-line: max-line-length
    return this.http.put<UpdateUsuarioDTO>(`${this.usuarioURL}/updateUser/${nombreUsuario}`, updateUserDTO, {headers: this.httpHeaders});
  }
}
