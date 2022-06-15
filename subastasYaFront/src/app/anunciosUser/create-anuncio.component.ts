import { Component, OnInit } from '@angular/core';
import { AnuncioService } from '../service/anuncio.service';
import { TokenService } from '../service/token.service';
import { Anuncio } from '../models/anuncio';
import { Ciudad } from '../models/ciudad';
import { Departamento } from '../models/departamento';
import { Usuario } from '../models/usuario';
import { EstadoAnuncio } from '../models/estadoAnuncio';
import { Producto } from '../models/producto';
import { UsuarioService } from '../service/usuario.service';
import { TipoProducto } from '../models/tipoProducto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-anuncio',
  templateUrl: './create-anuncio.component.html',
  styleUrls: ['./create-anuncio.component.css']
})
export class CreateAnuncioComponent implements OnInit {

  nuevoAnuncio: Anuncio;
  nuevoProducto: Producto;
  anuncios: Anuncio[] = [];
  usuarios: Usuario[] = [];
  //atributos anuncio
  descripcion: string;
  fecha_inicio: Date;
  fecha_fin: Date;
  ciudad: any;
  departamento: any;
  usuario: Usuario;
  estado:EstadoAnuncio = EstadoAnuncio.ACTIVO;
  valor: number;
  nombreProducto:string;
  tipoProducto: TipoProducto;


  //enums
  tiposProducto: string[] = ["Electronica","Autos"];
  ciudades: string[] = ["BOGOTA","MEDELLIN"];
  

  constructor(
    private usuarioService: UsuarioService,
    private anuncioService: AnuncioService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.usuarioService.lista().subscribe(
      data => this.usuarios = data
    )
    this.tipoProducto = new TipoProducto("Autos","Autos para vender");
  }

  onSelectCity(value: any): void {  
    console.log("Holaaa") 
    this.ciudad = Ciudad.APARTADO;
    console.log(this.ciudad)
    this.departamento = Departamento.QUINDÍO;
  }


  findUserByUserName(userName: string) {
    let user = null;
    this.usuarios.forEach(element => {
      if(element.nombreUsuario == userName){
        user = element;
        console.log(user)
      }
    });
    return user;
  }

  onCreate(){
    let user = this.findUserByUserName(this.tokenService.getUserName());
    this.usuario = user;
    this.nuevoProducto = new Producto(this.nombreProducto,"fotooo");
    this.nuevoAnuncio = new Anuncio(this.descripcion,this.fecha_inicio,this.fecha_fin,this.usuario,this.estado,this.ciudad,this.departamento,this.nuevoProducto,this.valor);
    console.log(this.nuevoProducto);
    this.anuncioService.createAnuncio(this.nuevoAnuncio).subscribe(
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
