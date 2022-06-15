import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  productoURL = 'http://localhost:8080/producto/';

  constructor(private http: HttpClient) { }

  createProducto(producto: Producto): Observable<any> {
    console.log("llegue a service")
    console.log(
      'llegooooo un ',producto
    )
    return this.http.post<any>(this.productoURL + 'create', producto);
  }
  
}
