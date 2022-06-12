import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { AuthService } from '../service/auth.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./login.component.css']
})
export class RegistroComponent implements OnInit {
  isLogged = false;

  nuevoUsuario: NuevoUsuario;
  nombres: string;
  apellidos: string;
  tipoDocumento: string;
  numeroDocumento: string;
  fechaNacimiento: Date;
  email: string;
  nombreUsuario: string;
  password: string;
  departamento: string;
  direccion: string;

  selectedDocType: any;
  selectedDepartment: any;

  errMsj: string;

  docTypes: string[] = ['Cedula de Ciudadania', 'Pasaporte', 'Cedula de Extranjeria'];

  departments: string[] = ['Amazonas', 'Antioquia', 'Arauca', 'Atlantico', 'Bolivar', 'Boyaca', 'Caldas',
    'Caqueta', 'Casanare', 'Cauca', 'Cesar', 'Choco', 'Cordoba', 'Cundinamarca', 'Guainia', 'Guaviare', 'Huila',
    'La guajira', 'Magdalena', 'Meta', 'Nariño', 'Norte de Santander', 'Putumayo', 'Quindio', 'Risaralda',
    'San Andres y Providencia', 'Santander', 'Sucre', 'Tolima', 'Valle del cauca', 'Vaupes', 'Vichada'];

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onSelectDocType(value: any): void {
    this.tipoDocumento = value;
  }

  onSelectDepartment(value: any): void {
    this.departamento = value;
  }

  onRegister(): void {
    // tslint:disable-next-line: max-line-length
    this.nuevoUsuario = new NuevoUsuario(this.nombres, this.apellidos, this.tipoDocumento, this.numeroDocumento, this.fechaNacimiento, this.email, this.nombreUsuario, this.password, this.departamento, this.direccion);

    console.log(this.nuevoUsuario);
    console.log(this.selectedDocType);
    console.log(this.selectedDepartment);

    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data => {
        this.toastr.success('Cuenta Creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });

        this.router.navigate(['/login']);
      },
      err => {
        this.toastr.error(this.errMsj, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });

        this.errMsj = err.error.mensaje;
        // console.log(err.error.message);
      }
    );
  }

}
