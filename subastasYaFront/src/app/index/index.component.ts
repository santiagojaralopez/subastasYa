import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { AnuncioService } from '../service/anuncio.service';
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
  ) { }

  ngOnInit() {
    this.anuncioService.listaActivos().subscribe(
      data => this.anuncios = data
    )
    console.log(this.anuncios)
    
  }

}
