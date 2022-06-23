import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import Swal from 'sweetalert2';


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

  onUnblock(username: string) {
    Swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea desbloquear al usuario: `+username,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, desbloquear!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.usuarioService.unBlockUser(username).subscribe(
          response => {
            Swal.fire(
              'Usuario debloqueado!',
              `El usuario fue bloqueao con exito`,
              'success'
            )
          }
        )
      }
    })

  }

  onBlock(username: string){
    Swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea bloquear al usuario: `+username,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, bloquear!',
      cancelButtonText: 'No, cancelar!'
     
    }).then((result) => {
      if (result.isConfirmed) {
        this.usuarioService.blockUser(username).subscribe(
          response => {
            Swal.fire(
              'Usuario Bloqueado!',
              `El usuario fue bloqueao con exito`,
              'success'
            )
          }
        )
      }
    })

  }

}
