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
import { ProductoService } from '../service/producto.service';
import { CiudadService } from '../service/ciudad.service';
import Swal from 'sweetalert2';
import { TipoProductoService } from '../service/tipoProducto.service';

//JAVASCRIPT
declare function boton(): any;
declare function mandarUrl():any;
declare let imagenUrl;
declare function miFuncion(result):any;


@Component({
  selector: 'app-create-anuncio',
  templateUrl: './create-anuncio.component.html',
  styleUrls: ['./create-anuncio.component.css']
})
export class CreateAnuncioComponent implements OnInit {

  
  nuevoAnuncio: Anuncio;
  nuevoProducto: Producto;
  
  
  imgUrl="assets/images/user-placeholder.png";
  //imgUrl="https://res.cloudinary.com/subastasya/image/upload/v1655968196/n4p9ehyclyvhy39y654j.jpg";
  //imgUrl=miFuncion(result);

  ciudades: Ciudad[] = [];
  productos: Producto[] = [];
  anuncios: Anuncio[] = [];
  usuarios: Usuario[] = [];
  tipos: TipoProducto[] = [];


  selectedTipo: TipoProducto;
  selectedCity: Ciudad;


  //atributos anuncio
  descripcion: string;
  fecha_inicio: Date;
  fecha_fin: Date;
  ciudad: Ciudad;
  departamento: any;
  usuario: Usuario;
  estado:EstadoAnuncio;
  valor: number;
  nombreProducto:string;
  tipoProducto: TipoProducto;
  fotoProducto: string;


  //enums
  

  constructor(
    private usuarioService: UsuarioService,
    private productoService: ProductoService,
    private anuncioService: AnuncioService,
    private ciudadService: CiudadService,
    private tokenService: TokenService,
    private tipoProductoService: TipoProductoService
  ) {
    
   }

  ngOnInit() {
    
    
    this.usuarioService.lista().subscribe(
      data => this.usuarios = data
    );
    this.ciudadService.lista().subscribe(
      data => this.ciudades = data
    );
    this.tipoProductoService.lista().subscribe(
      data => this.tipos = data
    );
    
  }

  onSelectType(value: any): void {
    this.tipoProducto = value;
  }

  onSelectCity(value: any): void {
    this.ciudad = value;
  }

  findUserByUserName(userName: string) {
    console.log('entre')

    let user = null;
    this.usuarios.forEach(element=>{
      if(element.nombreUsuario == userName){
        user = element;
        console.log(user)
      }
    });
    return user;
  }


  onCreate(){
    this.nuevoProducto = new Producto(this.nombreProducto,this.fotoProducto,this.tipoProducto);
    
    console.log(this.nuevoProducto.foto_producto)
    
    let user = this.findUserByUserName(this.tokenService.getUserName());
    this.usuario = user;
    
    console.log("holaaa url imagen: "+this.imgUrl)
    this.nuevoAnuncio = new Anuncio(this.descripcion,this.usuario,this.ciudad,this.nuevoProducto,this.valor);
    
    console.log(this.nuevoAnuncio);

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


botonFoto(){
  boton();
}

}