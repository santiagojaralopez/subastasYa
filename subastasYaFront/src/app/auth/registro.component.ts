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
  nombre: string;
  nombreUsuario: string;
  email: string;
  password: string;
  errMsj: string;
  departaments: string[] = ['Amazonas','Antioquia','Arauca','Atlantico','Bolivar','Boyaca','Caldas',
'Caqueta','Casanare','Cauca','Cesar','Choco','Cordoba','Cundinamarca','Guainia','Guaviare','Huila',
'La guajira','Magdalena','Meta','Narino','Norte de Santander','Putumayo','Quindio','Risaralda',
'San Andres y Providencia','Santander','Sucre','Tolima','Valle del cauca','Vaupes','Vichada'];

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

  onRegister(): void {
    this.nuevoUsuario = new NuevoUsuario(this.nombre, this.nombreUsuario, this.email, this.password);
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
