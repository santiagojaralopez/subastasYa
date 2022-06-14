import { Component, OnInit } from '@angular/core';
import { Producto } from '../models/producto';
import { AnuncioService } from '../service/anuncio.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import { Anuncio } from '../models/anuncio';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit {
  anuncios: Anuncio[] = [];
  roles: string[] = [];
  isAdmin = false;

  constructor(
    private anuncioService: AnuncioService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.anuncioService.lista().subscribe(
      data => this.anuncios = data
    )
    console.log(this.anuncios);
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }
}
