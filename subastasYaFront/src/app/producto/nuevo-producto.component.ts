import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../service/producto.service';
import { Producto } from '../models/producto';
import { Router } from '@angular/router';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-nuevo-producto',
  templateUrl: './nuevo-producto.component.html'
})
export class NuevoProductoComponent implements OnInit {

  nombre = '';
  precio: number = null;

  constructor(
    private productoService: ProductoService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    const producto = new Producto(this.nombre, this.precio);
    this.productoService.save(producto).subscribe(
      data => {
        Swal.fire(
          'Realizado!',
          'Producto eliminado',
          'success'
        );

        this.router.navigate(['/lista']);
      },
      err => {
        Swal.fire(
          'Error',
          'Ha ocurrido un error',
          'error'
        );
      }
    );
  }

}
