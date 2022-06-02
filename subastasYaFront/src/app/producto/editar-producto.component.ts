import { Component, OnInit } from '@angular/core';
import { Producto } from '../models/producto';
import { ProductoService } from '../service/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html'
})
export class EditarProductoComponent implements OnInit {

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

        this.router.navigate(['/lista']);
      }
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.productoService.update(id, this.producto).subscribe(
      data => {
        Swal.fire(
          'Realizado!',
          'Producto actualizado',
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
