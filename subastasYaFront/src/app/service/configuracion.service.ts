import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { Configuracion } from '../models/configuracion'
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracionService {

  tipoProductoURL = 'http://localhost:8080/configuracion/';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http: HttpClient){}

  updateConfiguration(id: number, config: Configuracion): Observable<Configuracion> {
    console.log("llegue al service")
    // tslint:disable-next-line: max-line-length
    return this.http.put<Configuracion>(`${this.tipoProductoURL}update-config/${id}`, config,{headers: this.httpHeaders});
  }

  getEdad(): Observable<Configuracion>{
    return this.http.get<Configuracion>(`${this.tipoProductoURL}detailConfig/${1}`);
  }

  getDuracion(): Observable<Configuracion>{
    return this.http.get<Configuracion>(`${this.tipoProductoURL}detailConfig/${2}`);
  }

  getOfertas(): Observable<Configuracion>{
    return this.http.get<Configuracion>(`${this.tipoProductoURL}detailConfig/${3}`);
  }

  getAnuncios(): Observable<Configuracion>{
    return this.http.get<Configuracion>(`${this.tipoProductoURL}detailConfig/${4}`);
  }

    
}