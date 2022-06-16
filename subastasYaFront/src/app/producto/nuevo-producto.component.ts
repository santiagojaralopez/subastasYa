import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../service/producto.service';
import { Producto } from '../models/producto';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

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

}
