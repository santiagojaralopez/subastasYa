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
<<<<<<< HEAD
    const id = this.activatedRoute.snapshot.params.id;
    this.productoService.detail(id).subscribe(
      data => {
        this.producto = data;
      },
      err => {
        Swal.fire(
          'Error',
          err.error.mensaje,
          'error'
        );
        this.router.navigate(['/lista']);
      }
    );
=======
    
>>>>>>> santiago-gallego
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
<<<<<<< HEAD
    this.productoService.update(id, this.producto).subscribe(
      data => {
        Swal.fire(
          'Éxito',
          'Producto Actualizado',
          'success'
        );
        this.router.navigate(['/lista']);
      },
      err => {
        Swal.fire(
          'Error',
          err.error.mensaje,
          'error'
        );
      }
    );
=======
    
>>>>>>> santiago-gallego
  }

}
