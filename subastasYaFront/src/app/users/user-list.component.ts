import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  usuarios: Usuario[] = [];
  roles: string[] = [];
  isAdmin = false;

  constructor(
    private usuarioService: UsuarioService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.usuarioService.lista().subscribe(
      data => this.usuarios = data
    );
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

}
