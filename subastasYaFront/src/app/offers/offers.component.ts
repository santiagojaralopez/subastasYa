import { Component, OnInit } from '@angular/core';
import { OfferDTO } from '../models/offer-dto';
import { OfertaService } from '../service/oferta.service';
import { TokenService } from '../service/token.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {

  ofertas: OfferDTO[] = [];
  roles: string[] = [];
  isAdmin = false;

  constructor(
    private offerService: OfertaService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  getOffers(id: number) {
    this.offerService.listOffersByAnnouncement(id).subscribe(
      data => this.ofertas = data
    );
  }

}
