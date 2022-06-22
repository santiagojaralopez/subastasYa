import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { element } from 'protractor';
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
  idDelete: number;

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

  onDelete(nombreTipo:string){
    console.log('a borrar',nombreTipo);
    this.findIdByNombre(nombreTipo);
    console.log(this.idDelete);
    Swal.fire({
      title: 'Está seguro??',
      text: `¿Seguro que desea eliminar la categoria: `+nombreTipo,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.tipoProductoService.deleteTipo(this.idDelete).subscribe(
          response => {
            Swal.fire(
              'Categoria Eliminada!',
              `Categoria eliminada con éxito.`,
              'success'
            )
          }
        )
      }
    })
  }

  onSelectUpdate(nombre: string, descripcion: string){
    Swal.fire({
        title: "Editar",
        text: "Ingrese el nuevo nombre para la categoria: "+nombre,
        input: "text",
        showCancelButton: true,
        confirmButtonText: "Aceptar",
        cancelButtonText: "Cancelar",
    })
    .then(async resultado => {
        if (resultado.value) {
            let nombre2 = resultado.value;
            this.nuevoNombre = nombre2;
            this.findIdByNombre(nombre);
            await new Promise(f => setTimeout(f, 100));
            this.onUpdate1(descripcion)
        }
    });
  }

  findIdByNombre(nuevoNombre: string){
    this.tipos.forEach(element=>{
      if(element.nombre_tipo == nuevoNombre) {
        this.idUpdate = element.idtipo_producto
        this.idDelete = element.idtipo_producto;
      }
    })
  }

  onUpdate1(descripcion: string){
    Swal.fire({
      title: "Editar",
      text: "Ingrese la nueva descripcion, la actual es: "+descripcion,
      input: "text",
      showCancelButton: true,
      confirmButtonText: "Aceptar",
      cancelButtonText: "Cancelar",
  })
  .then(async resultado => {
      if (resultado.value) {
          let descripcion  = resultado.value;
          this.nuevaDescripcion = descripcion;
          await new Promise(f => setTimeout(f, 100));
          this.onUpdate2();
      }
  });
  }

  onUpdate2(){
    this.tipoUpdate = new TipoProducto(this.nuevoNombre, this.nuevaDescripcion)
    this.tipoProductoService.updateTipo(this.idUpdate, this.tipoUpdate).subscribe(
      data => {
        Swal.fire(
          'Éxito',
          'La informacion ha sido actualizada con éxito',
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
