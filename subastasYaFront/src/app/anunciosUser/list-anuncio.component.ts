import { Component, OnInit } from '@angular/core';
import { Anuncio } from '../models/anuncio';
import { AnuncioService } from '../service/anuncio.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-list-anuncio',
  templateUrl: './list-anuncio.component.html',
  styleUrls: ['./list-anuncio.component.css']
})

export class ListAnuncioComponent implements OnInit {

  anuncios: Anuncio[] = [];

  constructor(
    private anuncioService: AnuncioService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    let username = this.tokenService.getUserName();
    this.anuncioService.listByUser(username).subscribe(
      data => this.anuncios = data
    );
  }

}
