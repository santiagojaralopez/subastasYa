import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../service/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Producto } from '../models/producto';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html'
})
export class DetalleProductoComponent implements OnInit {

  producto: Producto = null;

  constructor(
    private productoService: ProductoService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.productoService.detail(id).subscribe(
      data => {
        this.producto = data;
      },
      err => {
        Swal.fire(
          'Error',
          'Ha ocurrido un error',
          'error'
        );

        this.volver();
      }
    );
  }

  volver(): void {
    this.router.navigate(['/lista']);
  }

}
