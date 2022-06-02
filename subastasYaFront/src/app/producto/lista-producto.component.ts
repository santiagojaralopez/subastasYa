import { Component, OnInit } from '@angular/core';
import { Producto } from '../models/producto';
import { ProductoService } from '../service/producto.service';
import { TokenService } from '../service/token.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
})
export class ListaProductoComponent implements OnInit {
  productos: Producto[] = [];
  roles: string[];
  isAdmin = false;

  constructor(
    private productoService: ProductoService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarProductos();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  cargarProductos(): void {
    this.productoService.lista().subscribe(
      (data) => {
        this.productos = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  borrar(id: number) {
    this.productoService.delete(id).subscribe(
      data => {
        Swal.fire(
          'Realizado!',
          'Producto eliminado',
          'success'
        );

        this.cargarProductos();
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
