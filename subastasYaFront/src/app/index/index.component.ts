import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { AnuncioService } from '../service/anuncio.service';
import { Anuncio } from '../models/anuncio';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { OfferDTO } from '../models/offer-dto';
import { OfertaService } from '../service/oferta.service';

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

  offerDto: OfferDTO;
  offerValue: number;

  constructor(
    private anuncioService: AnuncioService,
    private tokenService: TokenService,
    private offerService: OfertaService,
    private router: Router
  ) { }

  ngOnInit() {
    this.anuncioService.listaActivos().subscribe(
      data => this.anuncios = data
    );

    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
    }

    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  onOffer(announcementId: number) {
    if (!this.isLogged) {
      this.router.navigate(['/login']);
    } else {
      Swal.fire({
        title: '<strong>Pujar por este producto</strong>',
        input: 'number',
        inputAttributes: {
          autocomplete: 'off',
          placeholder: 'Ingrese el valor en Pesos Colombianos'
        },
        inputLabel: '¿Cuánto quieres pujar por este anuncio?',
        inputValue: this.offerValue,
        showCloseButton: true,
        showDenyButton: true,
        focusConfirm: false,
        confirmButtonText: 'Pujar',
        denyButtonText: 'Cancelar',
        inputValidator: (value) => {
          if (!value) {
            return 'Debes asignar un valor a tu puja!';
          } else {
            this.offerValue = parseFloat(value);
          }
        }
      }).then((result) => {
        if (result.isConfirmed) {
          this.sureAboutOffering(announcementId);
        } else if (result.isDenied) {
          this.offerValue = undefined;
        }
      });
    }
  }

  sureAboutOffering(announcementId: number) {
    Swal.fire({
      title: 'Confirmar puja',
      showDenyButton: true,
      confirmButtonText: 'Confirmar',
      denyButtonText: `Cancelar`,
    }).then((result) => {
      if (result.isConfirmed) {
        this.offerDto = new OfferDTO(this.nombreUsuario, announcementId, this.offerValue);
        this.offerService.newOffer(this.offerDto).subscribe(
          data => {
            Swal.fire('Puja realizada!', 'Has pujado por un valor de COP$' + this.offerValue, 'success');
            this.offerValue = undefined;
          },
          err => {
            Swal.fire('Ups!', 'Parece que ha ocurrido un error. Intenta de nuevo más tarde', 'error');
          }
        );
      } else if (result.isDenied) {
        Swal.fire('Se ha cancelado tu proceso de puja', '', 'info');
        this.offerValue = undefined;
      }
    });
  }

}
