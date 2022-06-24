import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Anuncio } from '../models/anuncio';
import { EstadoAnuncio } from '../models/estadoAnuncio';
import { AnuncioService } from '../service/anuncio.service';
import { TokenService } from '../service/token.service';


@Component({
  selector:'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.css']

})

export class DetalleProductoComponent implements OnInit {
  estado = true;
  anuncio: Anuncio;
  imagen: string;

  constructor(
    private anuncioService: AnuncioService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private tokenService: TokenService
  ) { }

  
  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.anuncioService.getAnuncio(id).subscribe(
      data => {
        this.anuncio = data;
        this.foto();
      },
      err => {
        this.volver();
      }
    );
  }

  volver(): void {
    this.router.navigate(['/lista']);
  }

  estadoAnuncio(){
    if (this.anuncio.estado.id === 1 || this.anuncio.estado.id === 3) {
      this.estado = false;
    }
  }

  cambiarEstadoActivo(){
    this.anuncioService.updateAnuncioActivar(this.anuncio).subscribe(
      data => {
        document.getElementById("botonEstado").innerText= "ACTIVO";
        Swal.fire(
          'Exito',
          'Su anuncio ahora esta activo',
          'success'
        )
      },
      err => {
        Swal.fire(
          'Error',
          err.error.mensaje,
          'error'
        );
      }
    );
  }

  cambiarEstadoBloqueado(){
      this.anuncioService.updateAnuncioBloqueo(this.anuncio).subscribe(
        data => {
          document.getElementById("botonEstado").innerText= "BLOQUEADO";
          Swal.fire(
            'Exito',
            'Su anuncio ahora esta BLOQUEADO',
            'success'
          )
        }
        ,
        err => {
          Swal.fire(
            'Error',
            err.error.mensaje,
            'error'
          );
        }
      );
  }

  cambiarEstadoInactivo(){

    this.anuncioService.updateAnuncioInactivo(this.anuncio).subscribe(
      data => {
        document.getElementById("botonEstado").innerText= "INACTIVO";
        Swal.fire(
          'Exito',
          'Su anuncio ahora esta INACTIVO',
          'success'
        );
      },
      err => {
        Swal.fire(
          'Error',
          err.error.mensaje,
          'error'
        );
      }
    );
  }

  
  cambiarEstadoVendido(){
    this.anuncioService.updateAnuncioVendido(this.anuncio).subscribe(
      data => {
        document.getElementById("botonEstado").innerText= "VENDIDO";
        Swal.fire(
          'Exito',
          'Su anuncio ahora esta VENDIDO',
          'success'
        )
      },
      err => {
        Swal.fire(
          'Error',
          err.error.mensaje,
          'error'
        );
      }
    );
  }


  foto(){
    this.imagen=this.anuncio.producto.foto_producto;
    console.log(this.imagen);
  }
}
