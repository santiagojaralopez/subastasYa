import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UpdateUsuarioDTO } from '../models/update-user-dto';
import { Usuario } from '../models/usuario';
import { AuthService } from '../service/auth.service';
import { TokenService } from '../service/token.service';
import { UsuarioService } from '../service/usuario.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-edit-profile',
  templateUrl: './user-edit-profile.component.html',
  styleUrls: ['./user-edit-profile.component.css']
})
export class UserEditProfileAdminComponent implements OnInit {
  updateUsuario: UpdateUsuarioDTO = new UpdateUsuarioDTO();

  userName: string;

  selectedDocType: any;
  selectedDepartment: any;

  docTypes: string[] = ['Cedula de Ciudadania', 'Pasaporte', 'Cedula de Extranjeria'];

  departments: string[] = ['Amazonas', 'Antioquia', 'Arauca', 'Atlantico', 'Bolivar', 'Boyaca', 'Caldas',
    'Caqueta', 'Casanare', 'Cauca', 'Cesar', 'Choco', 'Cordoba', 'Cundinamarca', 'Guainia', 'Guaviare', 'Huila',
    'La guajira', 'Magdalena', 'Meta', 'Nariño', 'Norte de Santander', 'Putumayo', 'Quindio', 'Risaralda',
    'San Andres y Providencia', 'Santander', 'Sucre', 'Tolima', 'Valle del cauca', 'Vaupes', 'Vichada'];

  constructor(
    private usuarioService: UsuarioService,
    private authService: AuthService,
    private tokenService: TokenService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    let username = this.activatedRoute.snapshot.params.username;

    this.userName = username;

    this.cargarUsuario(username);
  }

  volver(): void {
    this.router.navigate(['/']);
  }

  cargarUsuario(username: string) {
  
    this.usuarioService.getUsuarioByUserName(username).subscribe(res => {
      this.updateUsuario = res;
    });
  }

  onSelectDocType(value: any): void {
    this.updateUsuario.tipoDocumento = value;
  }

  onSelectDepartment(value: any): void {
    this.updateUsuario.departamento = value;
  }

  onUpdate(): void {
    console.log(this.updateUsuario);
    this.usuarioService.updateUser(this.userName, this.updateUsuario).subscribe(
      data => {
        Swal.fire(
          'Éxito',
          'Su información ha sido actualizada con éxito',
          'success'
        );
        this.tokenService.setUserName(this.updateUsuario.nombreUsuario);
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



