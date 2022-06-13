import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { AnuncioService } from '../service/anuncio.service';
import { ToastrService } from 'ngx-toastr';
import { Anuncio } from '../models/anuncio';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  anuncios: Anuncio[] = [];
  roles: string[] = [];
  isAdmin = false;

  isLogged = false;
  nombreUsuario = '';

  constructor(
    private anuncioService: AnuncioService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.anuncioService.listaActivos().subscribe(
      data => this.anuncios = data
    )
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
  }

}
