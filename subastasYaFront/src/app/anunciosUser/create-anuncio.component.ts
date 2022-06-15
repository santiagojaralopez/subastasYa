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

@Component({
  selector: 'app-create-anuncio',
  templateUrl: './create-anuncio.component.html',
  styleUrls: ['./create-anuncio.component.css']
})
export class CreateAnuncioComponent implements OnInit {

  anuncios: Anuncio[] = [];
  usuarios: Usuario[] = [];
  //atributos anuncio
  descripcion: string;
  fecha_inicio: Date;
  fecha_fin: Date;
  ciudad: Ciudad;
  departamento: Departamento;
  usuario: Usuario;
  estado:EstadoAnuncio = EstadoAnuncio.ACTIVO;
  valor: number;
  producto: Producto;
  

  constructor(
    private usuarioService: UsuarioService,
    private anuncioService: AnuncioService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.usuarioService.lista().subscribe(
      data => this.usuarios = data
    )
    let user = this.findUserByUserName(this.tokenService.getUserName());
    this.usuario = user;
  }

  findUserByUserName(userName: string) {
    let user = null;
    this.usuarios.forEach(element => {
      if(element.nombreUsuario == userName){
        user = element;
      }
    });
    return user;
  }

  createAnuncio(){

  }

}
