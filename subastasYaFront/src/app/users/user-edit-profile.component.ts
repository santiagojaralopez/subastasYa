import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-user-edit-profile',
  templateUrl: './user-edit-profile.component.html',
  styleUrls: ['./user-edit-profile.component.css']
})
export class UserEditProfileComponent implements OnInit {

  usuario: Usuario;

  constructor(
    private usuarioService: UsuarioService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.usuarioService.getUsuario(id).subscribe(
      data => {
        this.usuario = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }

  volver(): void {
    this.router.navigate(['/']);
  }

}
