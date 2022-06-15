import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { UpdateUsuarioDTO } from '../models/update-user-dto';
import { Usuario } from '../models/usuario';
import { AuthService } from '../service/auth.service';
import { TokenService } from '../service/token.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-user-edit-profile',
  templateUrl: './user-edit-profile.component.html',
  styleUrls: ['./user-edit-profile.component.css']
})
export class UserEditProfileComponent implements OnInit {
  updateUsuario: UpdateUsuarioDTO;

  nombres: string;
  apellidos: string;
  nombreUsuario: string;
  email: string;
  tipoDocumento: string;
  numeroDocumento: string;
  fechaNacimiento: Date;
  departamento: string;
  direccion: string;

  selectedDocType: any;
  selectedDepartment: any;

  usuario: Usuario;
  usuarios: Usuario[] = [];

  docTypes: string[] = ['Cedula de Ciudadania', 'Pasaporte', 'Cedula de Extranjeria'];

  departments: string[] = ['Amazonas', 'Antioquia', 'Arauca', 'Atlantico', 'Bolivar', 'Boyaca', 'Caldas',
    'Caqueta', 'Casanare', 'Cauca', 'Cesar', 'Choco', 'Cordoba', 'Cundinamarca', 'Guainia', 'Guaviare', 'Huila',
    'La guajira', 'Magdalena', 'Meta', 'Nariño', 'Norte de Santander', 'Putumayo', 'Quindio', 'Risaralda',
    'San Andres y Providencia', 'Santander', 'Sucre', 'Tolima', 'Valle del cauca', 'Vaupes', 'Vichada'];

  constructor(
    private usuarioService: UsuarioService,
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit() {
    this.usuarioService.lista().subscribe(
      data => {
        this.usuarios = data;
      }
    );
    setTimeout(() => {
      this.cargarUsuario();
      }, 100);
  }

  findUserByUserName(userName: string) {
    let user: Usuario = null;

    this.usuarios.forEach(element => {
      if (element.nombreUsuario === userName) {
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
    console.log(this.usuario);
  }

  onSelectDocType(value: any): void {
    this.tipoDocumento = value;
  }

  onSelectDepartment(value: any): void {
    this.departamento = value;
  }

  onUpdate(): void {
    // tslint:disable-next-line: max-line-length
    this.updateUsuario = new UpdateUsuarioDTO(this.nombres, this.apellidos, this.numeroDocumento, this.fechaNacimiento, this.direccion, this.numeroDocumento, this.nombreUsuario, this.email);

    console.log(this.updateUsuario);

    
  }

}
