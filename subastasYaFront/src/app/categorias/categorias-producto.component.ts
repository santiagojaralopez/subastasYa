import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TipoProducto } from '../models/tipoProducto';
import { TipoProductoService } from '../service/tipoProducto.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-categorias-producto',
  templateUrl: './categorias-producto.component.html',
  styleUrls: ['./categorias-producto.component.css']
})
export class CategoriasProductoComponent implements OnInit {

  tipos: TipoProducto[] = [];
  nuevoTipo: TipoProducto;
  idUpdate: number;
  tipoUpdate: TipoProducto;

  nuevoNombre: string;
  nuevaDescripcion: string;
  isLogged = false;
  nombreUsuario = '';

  constructor(
    private tokenService: TokenService,
    private router: Router,
    private tipoProductoService: TipoProductoService
  ) { }

  ngOnInit() {
    this.tipoProductoService.lista().subscribe(
      data => this.tipos = data
    );
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
    this.router.navigate(['/']);
  }

  onDelete(){

  }

  onSelectUpdate(nombre: string, descripcion: string){
    Swal.fire({
        title: "Editar",
        text: "Ingrese el nuevo nombre para la categoria: "+nombre,
        input: "text",
        showCancelButton: true,
        confirmButtonText: "Guardar",
        cancelButtonText: "Cancelar",
    })
    .then(resultado => {
        if (resultado.value) {
            let nombre = resultado.value;
            console.log("Hola, " + nombre);
        }
    });
  }

  onUpdate(){
    this.tipoProductoService.updateTipo(this.idUpdate, this.tipoUpdate).subscribe(
      data => {
        Swal.fire(
          'Éxito',
          'La informacion ha sido actualizada con éxito',
          'success'
        );
        window.location.reload();
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

  onCreate(){
    this.nuevoTipo = new TipoProducto(this.nuevoNombre,this.nuevaDescripcion);

    this.tipoProductoService.createTipo(this.nuevoTipo).subscribe(
      data => {
        Swal.fire(
          'Exito',
          'Su anuncio ha sido creado con exito',
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
  

}
