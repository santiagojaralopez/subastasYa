import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Anuncio } from '../models/anuncio';
import { Offer } from '../models/offer';
import { OfferDTO } from '../models/offer-dto';
import { AnuncioService } from '../service/anuncio.service';
import { OfertaService } from '../service/oferta.service';
import { TokenService } from '../service/token.service';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {

  ofertas: Offer[] = [];
  roles: string[] = [];
  isAdmin = false;

  constructor(
    private anuncioService: AnuncioService,
    private activatedRoute: ActivatedRoute,
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

    const id = this.activatedRoute.snapshot.params.id;

    this.anuncioService.getAnuncio(id).subscribe(
      data => {
        this.getOffers(data.id_anuncio);
      }
    );
  }

  getOffers(id: number) {
    this.offerService.listOffersByAnnouncement(id).subscribe(
      data => {
        this.ofertas = data;
      }
    );
  }

  onSelectOffer(id: number) {
    Swal.fire({
      title: '¿Quiere seleccionar esta puja como la ganadora?',
      showDenyButton: true,
      confirmButtonText: 'Confirmar',
      denyButtonText: `Cancelar`,
    }).then((result) => {
      if (result.isConfirmed) {
        this.offerService.deleteOffers(id).subscribe();
        window.location.reload();
      }
    });
  }

}
