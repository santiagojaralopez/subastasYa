import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Anuncio } from '../models/anuncio';
import { AnuncioService } from '../service/anuncio.service';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html'
})
export class DetalleProductoComponent implements OnInit {

  anuncio: Anuncio;

  constructor(
    private anuncioService: AnuncioService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.anuncioService.getAnuncio(id).subscribe(
      data => {
        this.anuncio = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.volver();
      }
    );
  }

  volver(): void {
    this.router.navigate(['/lista']);
  }

}
