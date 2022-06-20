import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  productoURL = 'http://localhost:8080/producto/';

  constructor(private http: HttpClient) { }

  createProducto(producto: Producto): Observable<any> {
    return this.http.post<any>(this.productoURL + 'create', producto);
  }

  lista(): Observable<Producto[]> {
    return this.http.get(this.productoURL + 'lista').pipe(
      map(response => response as Producto[])
    );
  
}
}
