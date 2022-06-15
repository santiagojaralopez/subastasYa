import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../models/usuario';
import { TokenService } from '../service/token.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-user-edit-profile',
  templateUrl: './user-edit-profile.component.html',
  styleUrls: ['./user-edit-profile.component.css']
})
export class UserEditProfileComponent implements OnInit {

  usuario: Usuario | undefined;
  usuarios: Usuario[] = [];

  constructor(
    private usuarioService: UsuarioService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit() {
    this.usuarioService.lista().subscribe(
      data=>{
        this.usuarios = data;
      }
    )
    setTimeout(() => {          
      this.cargarUsuario();
      }, 100)
  }

  findUserByUserName(userName: string) {
    console.log(this.usuarios)
    let user: Usuario = null;
    this.usuarios.forEach(element => {
      if(element.nombreUsuario == userName){
        user = element;
      }
    });
    return user;
  }

  volver(): void {
    this.router.navigate(['/']);
  }

  cargarUsuario() {
    let user = this.findUserByUserName(this.tokenService.getUserName());
    this.usuario = user;
    console.log(this.usuario)
  }

}



