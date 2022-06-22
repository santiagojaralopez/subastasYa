import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Anuncio } from '../models/anuncio';
import { AnuncioService } from '../service/anuncio.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  anuncios: Anuncio[] = [];
  isLogged = false;
  nombreUsuario = '';
  roles: string[] = [];
  isAdmin: boolean = false;

  constructor(
    private tokenService: TokenService,
    private anuncioService: AnuncioService,
    private router: Router
  ) { }

  ngOnInit() {
    this.anuncioService.listaActivos().subscribe(
      data => this.anuncios = data
    );
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
    this.router.navigate(['/']);
  }

}
